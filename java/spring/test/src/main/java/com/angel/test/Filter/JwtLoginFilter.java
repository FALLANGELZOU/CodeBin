package com.angel.test.Filter;

import com.angel.test.Entity.User;
import com.angel.test.Jwt.TokenAuthenticationHelper;
import com.angel.test.Service.VerifyCodeService;
import com.angel.test.util.Http.HttpResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:37 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: JWT登录过滤器
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    private final VerifyCodeService verifyCodeService;

    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager, VerifyCodeService verifyCodeService) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
        this.verifyCodeService = verifyCodeService;

    }

    /**
     * 提取用户账号密码进行验证
     * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        // 获取 User 对象
        // readValue 第一个参数 输入流，第二个参数 要转换的对象
        User user = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);
        // 验证码验证
        //verifyCodeService.verify(httpServletRequest.getSession().getId(), user.getVerifyCode());
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                username,
                user.getPassword(),
                user.getAuthorities()
        );
        // 添加验证的附加信息
        token.setDetails(user);
        // 进行登陆验证
        return getAuthenticationManager().authenticate(token);
    }
    /**
     * 登陆成功回调
     * */

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 登陆成功
        TokenAuthenticationHelper.addAuthentication(request, response ,authResult);
    }

    /**
     * 登陆失败回调
     * */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 向前端写入数据
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(HttpResult.error("failed!",failed)));
        out.flush();
        out.close();
    }


}
