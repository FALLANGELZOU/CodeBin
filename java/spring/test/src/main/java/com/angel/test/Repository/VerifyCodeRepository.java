package com.angel.test.Repository;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:59 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description:
 */
public interface VerifyCodeRepository {

    void save(String key, String code);

    String find(String key);

    void remove(String key);
}
