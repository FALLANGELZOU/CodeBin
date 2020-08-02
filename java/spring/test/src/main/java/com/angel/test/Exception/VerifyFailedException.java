package com.angel.test.Exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: Angel_zou
 * @Date: Created in 1:14 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 验证码异常处理
 */
public class VerifyFailedException extends AuthenticationException {
    public VerifyFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public VerifyFailedException(String msg) {
        super(msg);
    }
}