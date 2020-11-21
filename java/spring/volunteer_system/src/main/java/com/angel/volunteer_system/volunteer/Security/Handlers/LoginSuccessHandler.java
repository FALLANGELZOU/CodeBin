package com.angel.volunteer_system.volunteer.Security.Handlers;

import com.alibaba.fastjson.JSON;
import com.angel.volunteer_system.volunteer.Entity.User.MyUserImpl;
import com.angel.volunteer_system.volunteer.Entity.User.ResponseUserImpl;
import com.angel.volunteer_system.volunteer.util.HttpResult;
import com.angel.volunteer_system.volunteer.util.StringToRules;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:48 2020/11/20
 * @Connection: ahacgn@gmail.com
 * @Description: 登录成功
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("认证成功!");
        MyUserImpl temp = (MyUserImpl) authentication.getPrincipal();
        ResponseUserImpl myUser = ResponseUserImpl
                .builder()
                .username(temp.getUsername())
                .id(temp.getId())
                .rules((List<String>) StringToRules.ToString(authentication.getAuthorities()))
                .password("********")
                .build();
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write( JSON.toJSONString(HttpResult.ok(myUser)));
    }
}
