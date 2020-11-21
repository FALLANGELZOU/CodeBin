package com.angel.volunteer_system.volunteer.util;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Author: Angel_zou
 * @Date: Created in 1:06 2020/11/20
 * @Connection: ahacgn@gmail.com
 * @Description: 字符串转化成权限
 */
public class StringToRules {
    public static Collection<? extends GrantedAuthority> toRules(@NotNull Collection<String> strRules){
        return strRules.stream().map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName)).collect(Collectors.toList());
    }
    public static Collection<String>ToString(@NotNull Collection<? extends GrantedAuthority> rules){
        return rules.stream().map(rule -> rule.getAuthority().substring(5)).collect(Collectors.toList());
    }
}
