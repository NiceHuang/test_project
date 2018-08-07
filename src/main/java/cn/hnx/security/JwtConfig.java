package cn.hnx.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by viruser on 2018/8/7.
 */

@Configuration
public class JwtConfig {

    @Bean
    public FilterRegistrationBean jwtFilter(){
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TokenAuthorFilter());
        registrationBean.addUrlPatterns("/server/*");
        return registrationBean;
    }
}
