package com.angel.test.dao;

import com.angel.test.Entity.Table.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Angel_zou
 * @Date: Created in 9:31 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 用户表dao
 */
@Repository
public interface UserTableRepository extends JpaRepository<UserTable, Long> {
    UserTable findUserTableByUsername(String username);
    void deleteUserTableById(Long Id);
    void deleteUserTableByUsername(Long Id);
}
