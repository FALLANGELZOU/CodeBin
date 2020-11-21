package com.angel.volunteer_system.volunteer.Entity.User;

import com.angel.volunteer_system.volunteer.Entity.MyUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 11:39 2020/11/20
 * @Connection: ahacgn@gmail.com
 * @Description: 用于返还的用户实体
 */
@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserImpl implements MyUser {
    private String username;
    private String password;
    private Long id;
    private List<String> rules;
}
