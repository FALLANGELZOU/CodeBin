package com.angel.test.Service;

import java.awt.*;

/**
 * @Author: Angel_zou
 * @Date: Created in 1:02 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 生成验证码
 */
public interface GenerateImageService {

    Image imageWithDisturb(String string);
}
