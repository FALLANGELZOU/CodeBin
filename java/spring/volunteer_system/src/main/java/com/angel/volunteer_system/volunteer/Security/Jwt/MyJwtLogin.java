package com.angel.volunteer_system.volunteer.Security.Jwt;

import com.alibaba.fastjson.JSON;
import com.angel.volunteer_system.volunteer.Entity.MyUser;
import com.angel.volunteer_system.volunteer.Entity.User.MyUserImpl;
import com.angel.volunteer_system.volunteer.Entity.User.ResponseUserImpl;
import com.angel.volunteer_system.volunteer.util.HttpResult;
import com.angel.volunteer_system.volunteer.util.JwtUtils;
import com.angel.volunteer_system.volunteer.util.SpringUtil;
import com.angel.volunteer_system.volunteer.util.StringToRules;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 1:14 2020/11/20
 * @Connection: ahacgn@gmail.com
 * @Description: 登录时jwt的相关操作,比如获取token
 */

@Slf4j
public class MyJwtLogin extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private JwtUtils jwtUtils;


    public MyJwtLogin(){
        super();
        log.info("jwt");
    }
//    public MyJwtLogin(AuthenticationManager authenticationManager) {
//
//        this.authenticationManager = authenticationManager;
//    }




    /**
     * 登录成功
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */


    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        MyUserImpl myUser = (MyUserImpl) authResult.getPrincipal();
        if(jwtUtils == null) SpringUtil.getBean("jwtUtils");

        String token = jwtUtils.generateToken(myUser);
        response.addHeader(jwtUtils.getTokenHead(),jwtUtils.getTokenPrefix() + token);

    }

    /**
     * 登录失败
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        log.info("登录失败");
//        log.info(failed.getMessage());
//        String message;
//
//        if(failed.getMessage().equals("Bad credentials")) message = "用户不存在!";
//        else message = "密码不正确!";
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        out.write( JSON.toJSONString(HttpResult.error(message)));
        super.unsuccessfulAuthentication(request, response, failed);
    }

    /**
     * 请求登录的时候
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        if(request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                ||request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){

            //use jackson to deserialize json
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()){
                MyUserImpl authenticationBean = mapper.readValue(is, MyUserImpl.class);
                authRequest = new UsernamePasswordAuthenticationToken(
                        authenticationBean.getUsername(), authenticationBean.getPassword());
            }catch (IOException e) {
//                log.error("出大问题");
//                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            }finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }

        else {
            return super.attemptAuthentication(request, response);
        }



    }
}
