package com.angel.volunteer_system.volunteer.Security;

import com.angel.volunteer_system.volunteer.Security.Handlers.LoginFailureHandler;
import com.angel.volunteer_system.volunteer.Security.Handlers.LoginSuccessHandler;
import com.angel.volunteer_system.volunteer.Security.Handlers.MyLogoutSuccessHandler;
import com.angel.volunteer_system.volunteer.Security.Jwt.JwtAuthenticationFilter;
import com.angel.volunteer_system.volunteer.Security.Jwt.MyJwtLogin;
import com.angel.volunteer_system.volunteer.Security.Service.MyUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.swing.*;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:05 2020/11/19
 * @Connection: ahacgn@gmail.com
 * @Description: 配置核心类
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //  实现UserDetailService接口
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    //  让找不到用户的错误显示
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setUserDetailsService(myUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }



    //  密码加密
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //  配置放行资源
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                .antMatchers("/index","/static/**","/login","/test")
                //  放行swagger,没写，用不到233
                //.antMatchers()
                ;
    }





    //  配置url
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("配置核心类");

        http
                .cors()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                //.addFilterAt(new MyJwtLogin(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable().authorizeRequests()
               // .antMatchers("/index","/login").permitAll()
                .anyRequest().authenticated()
                //.anyRequest()
                //.access("@rbacauthorityservice.hasPermission(request,authentication)")
                .and()
                .formLogin()
                .and()
                .addFilter(myJwtLogin())
                .addFilter(authenticationFilter())

        ;
                //.addFilter(new JwtAuthenticationFilter(authenticationManager()))
                //.exceptionHandling().accessDeniedHandler(new RestAuthenticationAccessDeniedHandler())
                //.authenticationEntryPoint(new AuthEntryPoint());
    }

    @Bean
    JwtAuthenticationFilter authenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }
    //  注册MyLogin
    @Bean
    MyJwtLogin myJwtLogin() throws Exception{
        MyJwtLogin filter = new MyJwtLogin();
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        filter.setAuthenticationFailureHandler(loginFailureHandler);
        //  重用AuthenticationManager,不能少
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }


}





