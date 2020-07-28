package com.angel.demo.Service.Imp;

import com.angel.demo.Service.UserService;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:51 2020/7/7
 * @Connection: ahacgn@gmail.com
 * @Description: userService实现类
 */
@Service
public class UserServiceImp implements UserService {

    /**
     * 简单base64加密
     * @param password  密码
     * @return          加密后密码
     * @throws IOException
     */
    @Override
    public String encryptPassword(String password) throws IOException {

        byte[] temp = (new BASE64Decoder()).decodeBuffer(password);
        return (new String(temp));
    }
}
