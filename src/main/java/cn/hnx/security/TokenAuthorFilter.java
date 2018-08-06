package cn.hnx.security;

import cn.hnx.common.utils.ResponseMessage;
import cn.hnx.common.utils.ResultMessage;
import cn.hnx.common.utils.TokenUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Created by viruser on 2018/8/6.
 */

@Component
@WebFilter
public class TokenAuthorFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(TokenAuthorFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request  = (HttpServletRequest)  servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            Optional<Authentication> authentication = TokenUtil.verifyToken(request);
            if (authentication.isPresent()) {
                SecurityContextHolder.getContext().setAuthentication(authentication.get());
            }
            else{
                SecurityContextHolder.getContext().setAuthentication(null);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (JwtException e) {
            logger.error("token验证失败："+e.getMessage());
            responseErrorInfo(response,e.getMessage());
        }catch (Exception e) {
            logger.error("token验证失败："+e.getMessage());
            responseErrorInfo(response,e.getMessage());
        }finally {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }

    private void responseErrorInfo(HttpServletResponse response,String message){
        try {
            response.setHeader( "Content-type" , MediaType.APPLICATION_JSON_UTF8_VALUE );
            response.setCharacterEncoding( StandardCharsets.UTF_8.displayName() );
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setStatus(ResponseMessage.INVALID_TOKEN.getCode());
            resultMessage.setMessage(ResponseMessage.INVALID_TOKEN.getMessage());
            response.getWriter().print(JSON.toJSONString(resultMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
