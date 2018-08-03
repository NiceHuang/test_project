package cn.hnx.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by hnx on 2018/8/3.
 */

@Configuration
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()// 定义哪些URL需要被保护、哪些不需要被保护
                .anyRequest() // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .formLogin() //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login")
                .permitAll();
    }
}
