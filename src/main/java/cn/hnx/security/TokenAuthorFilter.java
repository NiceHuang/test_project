package cn.hnx.security;

import cn.hnx.common.utils.ResponseMessage;
import cn.hnx.common.utils.ResultMessage;
import cn.hnx.common.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

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
        if ("/login".equals(request.getRequestURI()) ||
                "/register".equals(request.getRequestURI()) ||
                "/error".equals(request.getRequestURI()) ||
                request.getRequestURI().contains("redis")){
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
        message.setStatus(ResponseMessage.PERMISSION_DENIED.getCode());
        message.setMessage(ResponseMessage.PERMISSION_DENIED.getMessage());
        message.setData(new HashMap<>());
        printWriter.print(message.toString());
        printWriter.flush();
        printWriter.close();
    }

    @Override
    public void destroy() {

    }
}
