package com.angel.test.dao;

import com.angel.test.Entity.Table.UserRoleTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Angel_zou
 * @Date: Created in 9:50 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 用户角色表dao
 */
@Repository
public interface UserRoleTableRepository extends JpaRepository<UserRoleTable,Long> {
    List<UserRoleTable> findUserRoleTablesByUsername(String username);
    void deleteUserRoleTablesByUsername(String username);

}
