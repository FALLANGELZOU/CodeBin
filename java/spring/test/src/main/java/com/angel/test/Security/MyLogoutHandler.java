package com.angel.test.Security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Angel_zou
 * @Date: Created in 17:37 2020/8/6
 * @Connection: ahacgn@gmail.com
 * @Description: 自定义logout处理器
 */
@Slf4j
@Component("myLogoutHandler")
public class MyLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    }
}
