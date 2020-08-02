package com.angel.test.Repository.Impl;

import com.angel.test.Repository.VerifyCodeRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Angel_zou
 * @Date: Created in 1:00 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 在内存中保存需要的验证码
 */
@Repository
public class InMemoryVerifyCodeRepositoryImpl implements VerifyCodeRepository {

    private static final int DEFAULT_VERIFY_CODE_LENGTH = 4;

    private static final Map<String, String> REPOSITORY = new ConcurrentHashMap<>();

    @Override
    public void save(String key, String code) {
        REPOSITORY.put(key, code);
    }

    @Override
    public String find(String key) {
        return REPOSITORY.get(key);
    }

    @Override
    public void remove(String key) {
        REPOSITORY.remove(key);
    }
}