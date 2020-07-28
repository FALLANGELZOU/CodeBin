package com.angel.demo.learn;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data   // 这是自动添加getter啥的
@Builder    // 构造者模式
@Slf4j  // 写日志的东西，加上,log.info()
public class HowUseLomBok {
    private String name;
    private String password;
    private String id;
}
