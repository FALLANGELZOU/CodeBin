package com.angel.demo.Controller;

import com.angel.demo.Entity.Table.UserTable;
import com.angel.demo.Service.UserService;
import com.angel.demo.util.Http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:34 2020/7/23
 * @Connection: ahacgn@gmail.com
 * @Description: user_jpa对应控制层
 */
@RestController
@RequestMapping(value = "user")
public class UserJpaController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService user_service;


    @PostMapping(value = "add")
    public Object addUser(@RequestBody UserTable user) throws IOException {
        UserTable temp = user_service.findByName(user.getName());
        if(temp == null){
            user.setPassword(user_service.encryptPassword(user.getPassword()));
            user_service.save(user);
            return HttpResult.ok(null);
        }else {
            return HttpResult.error(400,"已经存在此用户");
        }


    }

    @DeleteMapping(value = "delete")
    public void deleteUser(Long id) {
        user_service.deleteById(id);
    }

}
