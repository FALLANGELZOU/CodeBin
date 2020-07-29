package com.angel.demo.Security;

import com.angel.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: Angel_zou
 * @Date: Created in 23:20 2020/7/28
 * @Connection: ahacgn@gmail.com
 * @Description: security的配置类
 */
@Configuration
@EnableWebSecurity // 启用 Spring Security 的 Web 安全支持，并提供 Spring MVC 集成
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)// 实现方法级别的权限控制
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Qualifier
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 用来配置拦截保护的请求
     * 比如哪些请求放行，哪些请求需要验证
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    }

    /**
     * 配置用户签名服务，主要是 user-details 机制
     * 还可以赋予用户具体权限控制
     *
     * @param auth 签名管理器构造器，用于构建用户具体权限控制
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入数据库验证类，实际上在验证链中加入了一个 DaoAuthenticationProvider
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    /**
     * 用来配置 Filter 链
     *
     * @param web Spring Web Security 对象
     */
    @Override
    public void configure(WebSecurity web) {

        // 忽略 URI
        web.ignoring()
                .antMatchers(
                        "/checkVerifyCode",
                        "/",
                        "/woo/user/register",
                        "/v2/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/doc.html"
                        );

    }
}