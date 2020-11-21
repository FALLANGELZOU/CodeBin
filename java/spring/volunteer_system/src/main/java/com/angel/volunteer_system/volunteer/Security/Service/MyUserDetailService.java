package com.angel.volunteer_system.volunteer.Security.Service;

import com.angel.volunteer_system.volunteer.Entity.MyUser;
import com.angel.volunteer_system.volunteer.Entity.User.MyUserImpl;
import com.angel.volunteer_system.volunteer.Service.FindUserService;
import com.angel.volunteer_system.volunteer.util.StringToRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * @Author: Angel_zou
 * @Date: Created in 22:08 2020/11/19
 * @Connection: ahacgn@gmail.com
 * @Description: 实现了UserDetailService
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private FindUserService findUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //  硬编码先写
        if(!s.equals("test")) throw new UsernameNotFoundException("用户不存在");
        List<String> names = Arrays.asList(new String[]{"Root"});
        UserDetails user = MyUserImpl
                .builder()
                .username("test")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .id(new Long(1))
                .rules((List<? extends GrantedAuthority>) StringToRules.toRules(names))
                .build();
//        UserDetails user = findUserService.FindUserByUserName(s);
//        if(user == null) throw new UsernameNotFoundException("未找到用户!");

        return user;
    }
}
