package com.angel.demo.Service.Impl;

import com.angel.demo.Entity.Table.RoleTable;
import com.angel.demo.Entity.Table.UserRoleTable;
import com.angel.demo.Entity.Table.UserTable;
import com.angel.demo.Entity.User;
import com.angel.demo.Service.UserService;
import com.angel.demo.dao.RoleJpaRepository;
import com.angel.demo.dao.UserJpaRepository;
import com.angel.demo.dao.UserRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:51 2020/7/7
 * @Connection: ahacgn@gmail.com
 * @Description: userService实现类
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     *
     * @param password  密码
     * @return          加密后密码
     * @throws IOException
     */

    @Autowired
    private UserJpaRepository user_jpa_repository;

    @Autowired
    private UserRoleJpaRepository user_role_jpa_repository;

    @Autowired
    private RoleJpaRepository role_jpa_repository;


    @Override
    public String encryptPassword(String password) {
        //return password;
        String temp = new BCryptPasswordEncoder().encode(password);
        return temp;
    }

    @Override
    public UserTable findByName(String name) {
        return user_jpa_repository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        user_jpa_repository.deleteById(id);
    }



    @Override
    public void save(UserTable user) {
        user_jpa_repository.save(user);
        UserRoleTable userRoleTable = new UserRoleTable();
        userRoleTable.setUserId(user.getId());
        userRoleTable.setRoleId(new Long(3));
        user_role_jpa_repository.save(userRoleTable);
    }

    @Override
    public List<String> findRolesById(Long id) {
        List<String> roleNames = new ArrayList<>();
        List<UserRoleTable> userRoleTables = user_role_jpa_repository.findUserRoleTablesByUserId(id);
        List<Long> roleIds = new ArrayList<>();

        //  获得roleIds
        for (UserRoleTable user_role_table: userRoleTables){
            roleIds.add(user_role_table.getRoleId());
        }

        //  获得roleNames

        for (Long roleId:roleIds){
            RoleTable temp = role_jpa_repository.findRoleTableById(roleId);
            roleNames.add(temp.getName());
        }

        return roleNames;
    }

    @Override
    public void deleteUserRoleByUserId(Long id) {
        user_role_jpa_repository.deleteUserRoleTablesByUserId(id);
    }
}
