package com.angel.test.Jwt;

import com.angel.test.Entity.User;
import com.angel.test.util.Http.HttpResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:14 2020/8/1
 * @Connection: ahacgn@gmail.com
 * @Description: 处理认证过程中的验证和请求
 */
public class TokenAuthenticationHelper {
    /**
     * 未设置rememberMe时 token 过期时间
     * */
    private static final long EXPIRATION_TIME = 7200000;

    /**
     * 设置rememberMe时 cookie token 过期时间
     * */
    private static final int COOKIE_EXPIRATION_TIME = 1296000;

    private static final String SECRET_KEY = "IsSecretKey";
    public static final String COOKIE_TOKEN = "COOKIE-TOKEN";
    public static final String XSRF = "XSRF-TOKEN";

    /**
     * 设置登陆成功后令牌返回
     * */
    public static void addAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException{
        //  获得登录角色
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        //  遍历角色
        StringBuffer stringBuffer = new StringBuffer();
        authorities.forEach(grantedAuthority -> {
            stringBuffer.append(grantedAuthority.getAuthority()).append(",");
        });
        long expirationTime = EXPIRATION_TIME;
        int cookExpirationTime = -1;

        //  获取user信息
        User userDetails = (User) auth.getDetails();

        //  查看rememberMe
        if (userDetails.getRememberMe() != null && userDetails.getRememberMe()){
            expirationTime = COOKIE_EXPIRATION_TIME*1000;
            cookExpirationTime = COOKIE_EXPIRATION_TIME;
        }

        //  过期时间
        long expiration = System.currentTimeMillis() + expirationTime;

        //  生成JWT令牌
        String jwt = Jwts.builder()
                        //  用户名
                        .setSubject(auth.getName())
                        //  权限
                        .claim("authorities",stringBuffer)
                        //  过期时间
                        .setExpiration(new Date(expiration))
                        //  签名算法
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                        .compact();

        //  返还Cookie
        Cookie cookie = new Cookie(COOKIE_TOKEN, jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(cookExpirationTime);   //  关闭网页cookie失效
        response.addCookie(cookie);

        //  以json返还前端数据
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        //  设置安全密码
        userDetails.setPassword("**********");
        //  设置过期时间
        userDetails.setExpirationTime(new Long(expiration));
        //  设置权限
        userDetails.setPower(stringBuffer.toString());

        out.write(new ObjectMapper().writeValueAsString(HttpResult.ok(userDetails)));
        out.flush();
        out.close();
    }

    /**
     * 对请求的验证
     * */
    public static Authentication getAuthentication(HttpServletRequest request) {
        //  获取cookie,token
        Cookie cookie = WebUtils.getCookie(request,COOKIE_TOKEN);
        String token = cookie != null ? cookie.getValue() : null;

        if (token != null){
            //  把token储存在claims里面，方便之后查询
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            // 获取用户权限
            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(claims.get("authorities").toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            //  获取用户名称
            String username = claims.getSubject();
            if(username != null){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                usernamePasswordAuthenticationToken.setDetails(claims);
                return usernamePasswordAuthenticationToken;
            }
            return null;

        }
        return null;
    }
}
