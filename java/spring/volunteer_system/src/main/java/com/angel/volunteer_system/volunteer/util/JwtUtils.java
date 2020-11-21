package com.angel.volunteer_system.volunteer.util;

import com.angel.volunteer_system.volunteer.Entity.User.MyUserImpl;
import com.angel.volunteer_system.volunteer.RSA.RSAUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @Author: Angel_zou
 * @Date: Created in 10:56 2020/11/20
 * @Connection: ahacgn@gmail.com
 * @Description: jwt工具类
 */

@Component
public class JwtUtils implements Serializable {
    private static final long serialVersionUID = -3301605591108950415L;

    @Value("${jwt.secret}")
    private  String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.tokenHeader}")
    private String tokenHead;
    @Value("${jwt.tokenHead}")
    private String prefix;

    @Value("${rsa.key.publicKeyFile}")
    private String publicKeyFile;

    @Value("${rsa.key.privateKeyFile}")
    private String privateKeyFile;

    public String getTokenHead(){return tokenHead;}

    private Clock clock = DefaultClock.INSTANCE;

    public String generateToken(UserDetails userDetails) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) throws Exception {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);
        PrivateKey privateKey = RSAUtils.getPrivateKey(privateKeyFile);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.RS256,privateKey)
                .compact();
    }
    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration);
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        MyUserImpl user = (MyUserImpl) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername())
                && !isTokenExpired(token)
        );
    }
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        Claims claims = null;
        try {
            PublicKey publicKey = null;
            try{
                publicKey = RSAUtils.getPublicKey(publicKeyFile);
            }catch (Exception e){

            }
            claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            claims = e.getClaims();
        }
        return claims;
    }
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public String  getTokenPrefix() {
        return prefix;
    }
}
