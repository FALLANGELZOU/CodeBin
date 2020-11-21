package com.angel.volunteer_system.volunteer.Security.Jwt;

import com.angel.volunteer_system.volunteer.Service.FindUserService;
import com.angel.volunteer_system.volunteer.util.JwtUtils;
import com.angel.volunteer_system.volunteer.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Angel_zou
 * @Date: Created in 17:32 2020/11/20
 * @Connection: ahacgn@gmail.com
 * @Description: 凭证校验
 */
/**
 * @author: Lan
 * @date: 2019/4/8 15:28
 * @description:token 校验过滤器
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtUtils jwtTokenUtil;
    @Autowired
    private FindUserService findUserService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (jwtTokenUtil == null) {
            jwtTokenUtil = (JwtUtils) SpringUtil.getBean("jwtUtils");
        }
        String header = request.getHeader(jwtTokenUtil.getTokenHead());
        //当token为空或格式错误时 直接放行
        if (header == null || !header.startsWith(jwtTokenUtil.getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    /**
     * 这里从token中获取用户信息并新建一个token
     */

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {

        String token = header.replace(jwtTokenUtil.getTokenPrefix(), "");
        String principal = jwtTokenUtil.getUsernameFromToken(token);
        if (principal != null) {
            UserDetails user = findUserService.FindUserByUserName(principal);
            return new UsernamePasswordAuthenticationToken(principal, null, user.getAuthorities());
        }
        return null;
    }
}