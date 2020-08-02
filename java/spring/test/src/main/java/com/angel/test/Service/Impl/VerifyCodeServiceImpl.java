package com.angel.test.Service.Impl;

import com.angel.test.Exception.VerifyFailedException;
import com.angel.test.Repository.VerifyCodeRepository;
import com.angel.test.Service.GenerateImageService;
import com.angel.test.Service.VerifyCodeService;
import com.angel.test.util.VerifyCode.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * @Author: Angel_zou
 * @Date: Created in 0:53 2020/8/2
 * @Connection: ahacgn@gmail.com
 * @Description: 验证码服务实现
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
    private final VerifyCodeRepository verifyCodeRepository;

    private final GenerateImageService generateImageService;

    //private final SendMessageService sendMessageService;

    private final VerifyCodeUtil verifyCodeUtil;

    private static final long VERIFY_CODE_EXPIRE_TIMEOUT = 60000L;

    @Autowired
    public VerifyCodeServiceImpl(VerifyCodeRepository verifyCodeRepository, GenerateImageService generateImageService, VerifyCodeUtil verifyCodeUtil) {
        this.verifyCodeRepository = verifyCodeRepository;
        this.generateImageService = generateImageService;
        this.verifyCodeUtil = verifyCodeUtil;
    }

    private static String randomDigitString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    private static String appendTimestamp(String string) {
        return string + "#" + System.currentTimeMillis();
    }

    @Override
    public void send(String key) {
        String verifyCode = randomDigitString(verifyCodeUtil.getLen());
        String verifyCodeWithTimestamp = appendTimestamp(verifyCode);
        verifyCodeRepository.save(key, verifyCodeWithTimestamp);

    }

    @Override
    public void verify(String key, String code) {
        String lastVerifyCodeWithTimestamp = verifyCodeRepository.find(key);
        // 如果没有验证码，则随机生成一个
        if (lastVerifyCodeWithTimestamp == null) {
            lastVerifyCodeWithTimestamp = appendTimestamp(randomDigitString(verifyCodeUtil.getLen()));
        }
        String[] lastVerifyCodeAndTimestamp = lastVerifyCodeWithTimestamp.split("#");
        String lastVerifyCode = lastVerifyCodeAndTimestamp[0];
        long timestamp = Long.parseLong(lastVerifyCodeAndTimestamp[1]);
        if (timestamp + VERIFY_CODE_EXPIRE_TIMEOUT < System.currentTimeMillis()) {
            throw new VerifyFailedException("验证码已过期！");
        } else if (!Objects.equals(code, lastVerifyCode)) {
            throw new VerifyFailedException("验证码错误！");
        }
    }

    @Override
    public Image image(String key) {
        String verifyCode = randomDigitString(verifyCodeUtil.getLen());
        String verifyCodeWithTimestamp = appendTimestamp(verifyCode);
        Image image = generateImageService.imageWithDisturb(verifyCode);
        verifyCodeRepository.save(key, verifyCodeWithTimestamp);
        return image;
    }
}
