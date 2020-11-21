package com.angel.test.Controller;

import com.angel.test.Entity.Table.UserTable;
import com.angel.test.Service.Impl.CustomUserDetailsService;
import com.angel.test.Service.UserService;
import com.angel.test.util.Http.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Author: Angel_zou
 * @Date: Created in 10:28 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 用户控制层
 */
@RestController
@Slf4j
@RequestMapping(value = "api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "add")
    public Object addUser(@RequestBody UserTable user) throws IOException {
        //System.out.println("开始执行");
        UserTable temp = userService.findUserTableByUsername(user.getUsername());
        //System.out.println("查找完成");
        if(temp == null){
            user.setPassword(userService.passwordEncoder().encode(user.getPassword()));
            //System.out.println("储存");
            userService.save(user);
            user.setPassword("**********");
            return HttpResult.ok(user);
        }else {
            return HttpResult.error(400,"已经存在此用户");
        }

    }

    @GetMapping(value = "test")
    public String test(){
        return "test";
    }
    @PostMapping(value =  "a")
    public Object A(){return HttpResult.ok("Ok");}
}
