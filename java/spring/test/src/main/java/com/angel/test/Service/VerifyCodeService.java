package com.angel.test.Service;

import java.awt.*;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:52 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 验证码Service层
 */
public interface VerifyCodeService {

    void send(String key);

    void verify(String key, String code);

    Image image(String key);
}