package com.angel.volunteer_system.volunteer.Controller;

import com.angel.volunteer_system.volunteer.Entity.MyUser;
import com.angel.volunteer_system.volunteer.Entity.User.MyUserImpl;
import com.angel.volunteer_system.volunteer.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: Angel_zou
 * @Date: Created in 14:10 2020/11/17
 * @Connection: ahacgn@gmail.com
 * @Description: 用户控制器
 */
@RestController // Controller内的数据都是以json格式输出
@Slf4j
public class UserController {


    @PostMapping("/test")
    @PreAuthorize("hasAnyRole('ROLE_ROOT','ROLE_USER')")
    public HttpResult test(@RequestBody @Valid MyUserImpl user){
        return HttpResult.ok(user);
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAnyRole('ROLE_ROOT')")
    public HttpResult addUser(){
        return HttpResult.ok("");
    }
}
