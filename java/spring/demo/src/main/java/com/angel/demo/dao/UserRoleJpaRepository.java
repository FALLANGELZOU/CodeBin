package com.angel.demo.dao;

import com.angel.demo.Entity.Table.UserRoleTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @Author: Angel_zou
 * @Date: Created in 1:07 2020/7/29
 * @Connection: ahacgn@gmail.com
 * @Description: 用户角色dao
 */
public interface UserRoleJpaRepository extends JpaRepository<UserRoleTable,Long> {
    List<UserRoleTable> findUserRoleTablesByUserId(Long id);

}
