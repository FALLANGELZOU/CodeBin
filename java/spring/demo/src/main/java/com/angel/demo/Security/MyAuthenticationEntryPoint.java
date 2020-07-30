package com.angel.demo.Security;

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
 * @Date: Created in 17:10 2020/7/30
 * @Connection: ahacgn@gmail.com
 * @Description: 权限控制
 */
@Component("myAuthenticationEntryPoint")
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        if (e instanceof InsufficientAuthenticationException) {
            out.write("need authority"); //需要权限
        }
        else {
            out.write("unknown error");
        }
        out.close();
    }
}