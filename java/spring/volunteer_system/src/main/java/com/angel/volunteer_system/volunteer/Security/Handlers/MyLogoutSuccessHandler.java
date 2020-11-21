package com.angel.volunteer_system.volunteer.Security.Handlers;

import com.alibaba.fastjson.JSON;
import com.angel.volunteer_system.volunteer.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:42 2020/11/20
 * @Connection: ahacgn@gmail.com
 * @Description:
 */
@Slf4j
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Value("${jwt.tokenHeader}")
    private String tokenHead;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("注销成功!");
        httpServletResponse.addHeader(tokenHead,"");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write( JSON.toJSONString(HttpResult.ok("注销成功!")));
    }
}
