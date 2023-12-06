package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "itheima";

    /**
     * generate token according to claims map
     *
     * @param claims user data
     * @return token String
     */
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 3600 * 12))
                .sign(Algorithm.HMAC256(KEY));
    }

    /**
     * parse token and return claim map
     *
     * @param token token string
     * @return claim map
     */
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
