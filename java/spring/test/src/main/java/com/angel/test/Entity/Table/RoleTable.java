package com.angel.test.Entity.Table;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: Angel_zou
 * @Date: Created in 9:35 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 角色表
 */
@Data
@Entity
@Table(name = "role")
public class RoleTable implements Serializable {
    @Id
    private Long id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "description", nullable = true, length = 64)
    private String description;
}
