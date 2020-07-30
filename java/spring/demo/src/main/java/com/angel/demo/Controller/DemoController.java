package com.angel.demo.Controller;

import com.angel.demo.Entity.User;
import com.angel.demo.Service.UserService;
import com.angel.demo.util.Http.HttpResult;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController // Controller内的数据都是以json格式输出
@Slf4j
public class DemoController {

    @GetMapping("/hello")
    
    public Object hello(){
        return "hello";
    }

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    //  json数据
    @PostMapping("/testJson")
    public Object testJson(@RequestBody @Valid User user) throws IOException {
        log.info("testJson: body: " + user.toString());
        if(user.getUsername() == null || user.getPassword() == null){
            return HttpResult.error("名称或密码不能为空");
        }

        String password = userService.encryptPassword(user.getPassword());
        user.setPassword(password);
        return HttpResult.ok(user);
    }

    //  params
    @GetMapping("/testParams")
    //@RequestMapping(value = "/testParams", method = {RequestMethod.GET})
    public HttpResult testParams(@RequestParam(value = "name", required = false, defaultValue = "") String name){
        return HttpResult.ok(name);
    }

}
