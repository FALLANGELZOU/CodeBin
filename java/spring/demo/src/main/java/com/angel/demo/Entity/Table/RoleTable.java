package com.angel.demo.Entity.Table;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:47 2020/7/29
 * @Connection: ahacgn@gmail.com
 * @Description: 角色表
 */
@Data
@Entity
@Table(name = "role")
public class RoleTable implements Serializable {
    @Id
    private Long id;

    @Column(name = "role_name", nullable = false, length = 32)
    private String name;

    @Column(name = "role_description", nullable = true, length = 64)
    private String description;
}
