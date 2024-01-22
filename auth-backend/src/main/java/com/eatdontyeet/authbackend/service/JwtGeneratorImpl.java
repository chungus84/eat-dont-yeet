package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.security.SecurityContants;
import com.eatdontyeet.authbackend.shared.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtGeneratorImpl implements JwtGenerator {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Map<String, String> generateToken(UserDto user) {
        String token = "";
        token = Jwts.builder()
                .setSubject(user.getUserName())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, SecurityContants.SECRET_KEY)
                .compact();
        Map<String, String> tokenGen = new HashMap<>();
        tokenGen.put("token", token);
        tokenGen.put("userId", user.getUserId());
        return tokenGen;
    }
}
