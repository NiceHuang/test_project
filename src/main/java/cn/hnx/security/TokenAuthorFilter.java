package cn.hnx.security;

import cn.hnx.common.utils.ResponseMessage;
import cn.hnx.common.utils.ResultMessage;
import cn.hnx.common.utils.TokenUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
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
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        Claims claims = TokenUtil.verifyToken(request);
        if (claims == null){
            responseMessage(response);
            return;
        }
        request.setAttribute("claims", claims);
        filterChain.doFilter(servletRequest, servletResponse);
    }
    private void responseMessage(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf8");
        PrintWriter printWriter = response.getWriter();
        ResultMessage message = new ResultMessage();
        message.setStatus(ResponseMessage.INVALID_TOKEN.getCode());
        message.setMessage(ResponseMessage.INVALID_TOKEN.getMessage());
        message.setData(new HashMap<>());
        printWriter.print(message.toString());
        printWriter.flush();
        printWriter.close();
    }

    @Override
    public void destroy() {

    }
}
