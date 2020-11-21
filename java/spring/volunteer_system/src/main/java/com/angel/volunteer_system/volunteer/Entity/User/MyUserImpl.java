package com.angel.volunteer_system.volunteer.Entity.User;

import com.angel.volunteer_system.volunteer.Entity.MyUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:16 2020/11/19
 * @Connection: ahacgn@gmail.com
 * @Description: 简单实现MyUser
 */
@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class MyUserImpl implements MyUser , UserDetails {
    private String username;
    private String password;
    private Long id;
    private List<? extends GrantedAuthority> rules = null;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return rules;
    }

    @Override
    public String getUsername() {
        return username;
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
