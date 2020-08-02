package com.angel.test.Entity.Table;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Angel_zou
 * @Date: Created in 9:36 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 用户角色映射表
 */
@Data
@Entity
@Table(name = "user_role")
public class UserRoleTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "role_name", nullable = false)
    private String roleName;
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
//
//    @Column(name = "role_id", nullable = false)
//    private Long roleId;

}

