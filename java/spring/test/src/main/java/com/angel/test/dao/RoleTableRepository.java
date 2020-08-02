package com.angel.test.dao;

import com.angel.test.Entity.Table.RoleTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;

/**
 * @Author: Angel_zou
 * @Date: Created in 9:47 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 角色表dao
 */
@Repository
public interface RoleTableRepository extends JpaRepository<RoleTable,Long> {
    RoleTable findRoleTableByName(String name);
    RoleTable findRoleTableById(Long Id);
}
