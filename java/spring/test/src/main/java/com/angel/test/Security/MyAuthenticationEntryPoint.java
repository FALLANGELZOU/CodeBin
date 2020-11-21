package com.angel.test.Security;

import com.angel.test.util.Http.HttpResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:54 2020/8/6
 * @Connection: ahacgn@gmail.com
 * @Description: 无权限返还
 */
@Component("myAuthenticationEntryPoint")
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        if (e instanceof InsufficientAuthenticationException) {
            out.write(new ObjectMapper().writeValueAsString(HttpResult.send(403,"请登录!",null)));
            //out.write("need authority"); //需要权限
        }
        else {
            out.write(new ObjectMapper().writeValueAsString(HttpResult.send(400,"未知错误!",null)));
        }
        out.flush();
        out.close();
    }
}