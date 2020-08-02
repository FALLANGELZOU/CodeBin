package com.angel.test.Entity;


import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 21:58 2020/8/1
 * @Connection: ahacgn@gmail.com
 * @Description: user实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails,Serializable {

    private Long id;

    private String username;

    private String password;

    private Boolean rememberMe;

    private String verifyCode;

    private String power;

    private Long expirationTime;

    private List<GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
