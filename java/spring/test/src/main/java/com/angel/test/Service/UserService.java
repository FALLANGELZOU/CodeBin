package com.angel.test.Service;

import com.angel.test.Entity.Table.RoleTable;
import com.angel.test.Entity.Table.UserRoleTable;
import com.angel.test.Entity.Table.UserTable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 12:07 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description:
 */
public interface UserService {
    UserTable findUserTableByUsername(String username);
    void save(UserTable user);
    void save(UserTable userTable, UserRoleTable userRoleTable);
    List<UserRoleTable> findUserRoleTablesByUsername(String username);
    RoleTable findRoleTableByName(String roleName);
    public PasswordEncoder passwordEncoder();
}
