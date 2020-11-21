package com.angel.volunteer_system.volunteer.dao;

import com.angel.volunteer_system.volunteer.Entity.User.MyUserImpl;
import com.angel.volunteer_system.volunteer.util.StringToRules;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 17:39 2020/11/20
 * @Connection: ahacgn@gmail.com
 * @Description: user dao
 */

@Component
public class UserDAO {
    public UserDetails findUserById(Long id){ return null;}
    public UserDetails findUserByUsername(String Username){

        List<String> names = Arrays.asList(new String[]{"ROOT"});
        UserDetails user = MyUserImpl
                .builder()
                .username("test")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .id(new Long(1))
                .rules((List<? extends GrantedAuthority>) StringToRules.toRules(names))
                .build();
        return user;
    }


}
