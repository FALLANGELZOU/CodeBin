package com.angel.demo.Service;

import com.angel.demo.Entity.Table.UserTable;

import java.util.List;


/**
 * @Author: Angel_zou
 * @Date: Created in 22:50 2020/7/7
 * @Connection: ahacgn@gmail.com
 * @Description: user接口类
 */
public interface UserService {
    //  加密
    String encryptPassword(String password);

    //  封装dao层
    UserTable findByName(String name);
    void deleteById(Long id);
    void save(UserTable user);
    List<String> findRolesById(Long id);
    void deleteUserRoleByUserId(Long id);


}
