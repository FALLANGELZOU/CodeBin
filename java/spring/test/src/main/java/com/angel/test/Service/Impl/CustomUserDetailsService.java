package com.angel.test.Service.Impl;

import com.angel.test.Entity.Table.RoleTable;
import com.angel.test.Entity.Table.UserRoleTable;
import com.angel.test.Entity.Table.UserTable;
import com.angel.test.Entity.User;
import com.angel.test.Service.UserService;
import com.angel.test.dao.RoleTableRepository;
import com.angel.test.dao.UserRoleTableRepository;
import com.angel.test.dao.UserTableRepository;
import com.angel.test.util.Spring.SpringUtil;
import org.hibernate.dialect.CUBRIDDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 9:20 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 实现UserDetailsService
 */
@Service
@Configuration
public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    //@Qualifier("userTableRepository")
//    private UserTableRepository userTableRepository;
//    @Autowired
//    private UserRoleTableRepository userRoleTableRepository;
//    @Autowired
//    private RoleTableRepository roleTableRepository;
    @Autowired
        @Qualifier("userServiceImpl")
    UserService userService;
//    public CustomUserDetailsService(){
//        userTableRepository = (UserTableRepository) SpringUtil.getBean("userRoleTableRepository");
//    }
    public static CustomUserDetailsService customUserDetailsService;
    @PostConstruct
    public void init(){
        //System.out.println("***********************");
        customUserDetailsService = this;
        customUserDetailsService.userService = this.userService;

    }





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserTable userTable= customUserDetailsService.userService.findUserTableByUsername(username);
        if(userTable == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        List<UserRoleTable> userRoleTables = customUserDetailsService.userService.findUserRoleTablesByUsername(userTable.getUsername());
        List<GrantedAuthority> userRoles = new ArrayList<>();

        for (UserRoleTable userRoleTable:userRoleTables) {
            RoleTable roleTable = customUserDetailsService.userService.findRoleTableByName(userRoleTable.getRoleName());

            userRoles.add(new SimpleGrantedAuthority(roleTable.getName()));
        }


        return User.builder()
                .username(username)
                .password(userTable.getPassword())
                .authorities(userRoles)
                .build();

    }


}
