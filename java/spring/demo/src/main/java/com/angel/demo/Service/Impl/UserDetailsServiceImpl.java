package com.angel.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.angel.demo.Entity.Table.UserTable;
import com.angel.demo.Entity.User;
import com.angel.demo.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 8:38 2020/7/29
 * @Connection: ahacgn@gmail.com
 * @Description:
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService user_service;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println("调用了loadUserByUsername----------");
        UserTable user = user_service.findByName(name);
        if(user == null){
            throw new UsernameNotFoundException("用户未找到");
        }

        String password = user.getPassword();
        Long id = user.getId();

        List<String> roleNames =   user_service.findRolesById(id);
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String roleName:roleNames){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(grantedAuthority);
        }

        log.info("grantedAuthority:" + JSON.toJSONString(authorities) + "has added");


        return User.builder()
                .username(name)
                .password(password)
                .roles(authorities)
                .build();
    }
}
