package com.angel.volunteer_system.volunteer.RSA;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Author: Angel_zou
 * @Date: Created in 23:10 2020/11/20
 * @Connection: ahacgn@gmail.com
 * @Description:
**/

@Data
@Component
@ConfigurationProperties(prefix = "rsa.key")
@Slf4j
public class RSAKeyProperties {

    private String publicKeyFile;
    private String privateKeyFile;

    private PublicKey publicKey;
    private PrivateKey privateKey;
    private String secret;

    @PostConstruct
    public void createRSAKey() throws Exception {
        log.info("生成私钥和公钥");
        RSAUtils.generateKey(publicKeyFile, privateKeyFile, secret, 0);
        this.publicKey = RSAUtils.getPublicKey(publicKeyFile);
        this.privateKey = RSAUtils.getPrivateKey(privateKeyFile);

        String data = "123";
        String b = RSAUtils.encrypt(data,publicKey);
        log.info(b);
        String a = RSAUtils.decrypt(b,privateKey);
        log.info(a);

    }
}
