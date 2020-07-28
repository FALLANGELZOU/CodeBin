package com.angel.demo.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * @Author: Angel_zou
 * @Date: Created in 22:05 2020/7/7
 * @Connection: ahacgn@gmail.com
 * @Description: 用户实体类
 */

@Data
@Component
public class User implements Serializable {
    //  实现Serializable是为了持久化对象，即可以读写对象

    @NotBlank(message = "名称不能为空")
    private String name;
    @NotBlank(message = "密码不能为空")
    @Length(min = 5,max = 15,message = "密码长度请设置在5-15之间")
    private String password;
    @Email(message = "邮箱格式不对")
    private String email;


}
