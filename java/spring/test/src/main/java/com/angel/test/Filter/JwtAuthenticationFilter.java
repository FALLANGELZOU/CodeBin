package com.angel.test.Filter;

import com.angel.test.Jwt.TokenAuthenticationHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author: Angel_zou
 * @Date: Created in 1:39 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 解析用户token
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            Authentication authentication = TokenAuthenticationHelper.getAuthentication(httpServletRequest);

            // 对用 token 获取到的用户进行校验
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |
                SignatureException | IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired，登陆已过期");
        }
    }
}
