package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "zhangShan");
        String token = JWT.create()
                .withClaim("user", claims)//setup load
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))//setup deadline
                .sign(Algorithm.HMAC256("itheima"));//setup algorithm and secret
        System.out.println(token);
    }

    @Test
    public void testParseToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJleHAiOjE3MDE4MzE5OTMsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoiemhhbmdTaGFuIn19" +
                ".P-Y9jwh1u-LHHhNU2nQlOkRG5NAl2REm3ezoJeoHbLM";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);//check token
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}
