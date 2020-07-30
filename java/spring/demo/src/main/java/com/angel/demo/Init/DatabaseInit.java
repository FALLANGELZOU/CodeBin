package com.angel.demo.Init;

import com.angel.demo.Entity.Table.RoleTable;
import com.angel.demo.dao.RoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @Author: Angel_zou
 * @Date: Created in 22:29 2020/7/30
 * @Connection: ahacgn@gmail.com
 * @Description: 初始化数据库
 */
@Component
public class DatabaseInit {
    @Autowired
    RoleJpaRepository roleJpaRepository;

//    @Enumerated(EnumType.STRING)
//    private Roles roles;

    public enum Roles {

        Root,Admin,Normal

    }

    @PostConstruct
    public void init(){
        Long cnt = new Long(1);
        for (Roles t: Roles.values()){
            String temp = t.toString();
            //System.out.print(temp);
            RoleTable roleTable = roleJpaRepository.findRoleTableByName(temp);
            if(roleTable == null){
                RoleTable insert = new RoleTable();
                insert.setName(temp);
                insert.setId(cnt);
                roleJpaRepository.save(insert);
            }
            cnt += new Long(1);

        }
    }
}
