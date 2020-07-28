package com.angel.demo.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:13 2020/7/23
 * @Connection: ahacgn@gmail.com
 * @Description: jpa建立的映射实体类user
 */
@Data
@Entity
@Table(name = "user")
public class UserJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = true, length = 32)
    private String name;

    @Column(name = "user_password", nullable = true, length = 32)
    private String password;

    @Column(name = "user_level", nullable = true)
    private Integer level;
}
