package com.angel.demo.Config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Angel_zou
 * @Date: Created in 20:23 2020/7/18
 * @Connection: ahacgn@gmail.com
 * @Description: swagger配置类
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {
//  http://localhost:8080/doc.html
}
