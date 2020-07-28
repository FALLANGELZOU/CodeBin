package com.angel.demo.Service;

import java.io.IOException;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:50 2020/7/7
 * @Connection: ahacgn@gmail.com
 * @Description: user接口类
 */
public interface UserService {
    String encryptPassword(String password) throws IOException;
}
