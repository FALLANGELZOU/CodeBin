package com.angel.demo.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * @Author: Angel_zou
 * @Date: Created in 22:05 2020/7/7
 * @Connection: ahacgn@gmail.com
 * @Description: 用户实体类
 */
@Data
@Builder
@Component
@AllArgsConstructor
public class User implements UserDetails {
    //  实现Serializable是为了持久化对象，即可以读写对象

    @NotBlank(message = "名称不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(min = 5,max = 15,message = "密码长度请设置在5-15之间")
    private String password;
    @Email(message = "邮箱格式不对")
    private String email;
    @Null
    private List<GrantedAuthority> roles;// 权限集合


    User(){}


    //  权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //  用户有没有过期
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    //  用户有没有锁定
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    //  证书有没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    //  用户是否有效
    @Override
    public boolean isEnabled() {
        return false;
    }
}
