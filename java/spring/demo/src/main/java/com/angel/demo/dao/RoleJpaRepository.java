package com.angel.demo.dao;

import com.angel.demo.Entity.Table.RoleTable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;

/**
 * @Author: Angel_zou
 * @Date: Created in 1:07 2020/7/29
 * @Connection: ahacgn@gmail.com
 * @Description: 角色dao
 */
public interface RoleJpaRepository extends JpaRepository<RoleTable,Long> {
    RoleTable findRoleTableById(Long id);
    RoleTable findRoleTableByName(String name);
}
