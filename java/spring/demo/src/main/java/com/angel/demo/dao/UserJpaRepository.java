package com.angel.demo.dao;

import com.angel.demo.Entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:31 2020/7/23
 * @Connection: ahacgn@gmail.com
 * @Description: user_jpa对应dao层
 */
@Repository
public interface  UserJpaRepository extends JpaRepository<UserJpa, Long> {
    public UserJpa findByName(String name);

}
