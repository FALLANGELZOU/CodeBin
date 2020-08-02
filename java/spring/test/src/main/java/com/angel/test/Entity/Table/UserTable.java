package com.angel.test.Entity.Table;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Angel_zou
 * @Date: Created in 9:32 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 用户表
 */
@Data
@Entity
@Table(name = "user")
public class UserTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

}
