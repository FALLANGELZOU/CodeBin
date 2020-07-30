package com.angel.demo.Entity.Table;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:13 2020/7/23
 * @Connection: ahacgn@gmail.com
 * @Description: 角色表
 */
@Data
@Entity
@Table(name = "user")
public class UserTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 32)
    private String name;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_level", nullable = true)
    private Integer level;
}
