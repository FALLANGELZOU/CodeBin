package com.angel.demo.Controller;

import com.angel.demo.Entity.Table.UserTable;
import com.angel.demo.Entity.User;
import com.angel.demo.Service.UserService;
import com.angel.demo.util.Http.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: Angel_zou
 * @Date: Created in 17:40 2020/7/28
 * @Connection: ahacgn@gmail.com
 * @Description: 登陆控制层
 */

/**
 * 已经废弃，采用security框架
 */
@RestController
@Slf4j
//@RequestMapping(value = "login")
public class LoginController {


//    @Autowired
//    @Qualifier("userServiceImpl")
//    private UserService user_service;
//
//    @GetMapping(value = "login")
//    public String login(){
//        return "login";
//    }
//    public Object login(@RequestParam("name") String name,@RequestParam("password") String password) throws IOException {
//        UserTable temp = user_service.findByName(name);
//        if(temp != null){
//
//            if(temp.getPassword().equals(user_service.encryptPassword(password))){
//                return HttpResult.ok(200);
//            }else{
//                return HttpResult.error("密码错误！");
//            }
//        }else{
//            return HttpResult.error("用户不存在！");
//        }
//    }
}
