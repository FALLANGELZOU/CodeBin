package com.angel.demo.Entity.Table;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:47 2020/7/29
 * @Connection: ahacgn@gmail.com
 * @Description: 用户角色表
 */
@Data
@Entity
@Table(name = "user_role")
public class UserRoleTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

}
