package com.angel.volunteer_system.volunteer.Service;

import com.angel.volunteer_system.volunteer.Entity.User.MyUserImpl;
import com.angel.volunteer_system.volunteer.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:12 2020/11/19
 * @Connection: ahacgn@gmail.com
 * @Description: 获取用户信息，用来实现loadUserByUsername，security中
 */

/**
 *  11.19具体细节未实现
 */
@Service
public class FindUserService {

    @Autowired
    private UserDAO userDAO;


    public UserDetails FindUserByUserName(String userName){
        //  永远只返还第一个人
        return FindUsersByUserName(userName).get(0);
    }

    public MyUserImpl FindUserByID(Long ID){
        return null;
    }

    public List<? extends UserDetails> FindUsersByUserName(String userName){
        List<UserDetails> res = new ArrayList<>();

        res.add(userDAO.findUserByUsername(userName));
        return res;
    }
}
