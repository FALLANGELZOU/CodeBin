package com.angel.volunteer_system.volunteer.Security.Handlers;

import com.alibaba.fastjson.JSON;
import com.angel.volunteer_system.volunteer.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:52 2020/11/20
 * @Connection: ahacgn@gmail.com
 * @Description: 认证失败
 */
@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("认证失败");
        String message = "用户名或密码错误!";
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write( JSON.toJSONString(HttpResult.error(message)));
    }
}
