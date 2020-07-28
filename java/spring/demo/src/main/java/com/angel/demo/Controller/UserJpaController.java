package com.angel.demo.Controller;

import com.angel.demo.Entity.UserJpa;
import com.angel.demo.dao.UserJpaRepository;
import com.angel.demo.util.Http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private UserJpaRepository user_jpa_repository;

    @PostMapping(path = "add")
    public Object addUser(@RequestBody UserJpa user)
    {
        UserJpa temp = user_jpa_repository.findByName(user.getName());
        if(temp == null){
            user_jpa_repository.save(user);
            return HttpResult.ok(null);
        }else {
            return HttpResult.error(400,"已经存在此用户");
        }


    }

    @DeleteMapping(path = "delete")
    public void deleteUser(Long id) {
        user_jpa_repository.deleteById(id);
    }

}
