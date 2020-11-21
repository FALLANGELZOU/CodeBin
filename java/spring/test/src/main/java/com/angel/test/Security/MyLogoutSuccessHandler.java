package com.angel.test.Security;

import com.angel.test.util.Http.HttpResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @Author: Angel_zou
 * @Date: Created in 16:41 2020/8/6
 * @Connection: ahacgn@gmail.com
 * @Description: 注销成功处理器
 */
@Slf4j
@Component("myLogoutSuccessHandler")
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final String SECRET_KEY = "IsSecretKey";
    public static final String COOKIE_TOKEN = "COOKIE-TOKEN";
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //  生成新JWT令牌
        String jwt = Jwts.builder()
                //  用户名
                .setSubject("NONE")
                //  权限
                .claim("authorities","NONE")
                //  过期时间
                .setExpiration(new Date(System.currentTimeMillis()))
                //  签名算法
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        //  返还Cookie
        Cookie cookie = new Cookie(COOKIE_TOKEN, jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(-1);   //  关闭网页cookie失效
        httpServletResponse.addCookie(cookie);
        log.info("success");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(new ObjectMapper().writeValueAsString(HttpResult.ok(null)));
        out.flush();
        out.close();
    }
}