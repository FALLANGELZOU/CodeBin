package com.angel.test.Service.Impl;

import com.angel.test.Entity.Table.RoleTable;
import com.angel.test.Entity.Table.UserRoleTable;
import com.angel.test.Entity.Table.UserTable;
import com.angel.test.Service.UserService;
import com.angel.test.dao.RoleTableRepository;
import com.angel.test.dao.UserRoleTableRepository;
import com.angel.test.dao.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:06 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 封装用户dao
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserTableRepository userTableRepository;
    @Autowired
    private UserRoleTableRepository userRoleTableRepository;
    @Autowired
    private RoleTableRepository roleTableRepository;

    @Override
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserTable findUserTableByUsername(String username) {
        return userTableRepository.findUserTableByUsername(username);
    }

    @Override
    public void save(UserTable user) {
        userTableRepository.save(user);
    }

    @Override
    public void save(UserTable userTable, UserRoleTable userRoleTable) {
        userRoleTableRepository.save(userRoleTable);
        userTableRepository.save(userTable);
    }

    @Override
    public List<UserRoleTable> findUserRoleTablesByUsername(String username) {
        return userRoleTableRepository.findUserRoleTablesByUsername(username);
    }

    @Override
    public RoleTable findRoleTableByName(String roleName) {
        return roleTableRepository.findRoleTableByName(roleName);
    }
}
