package com.hiekn.demo.test.java;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.hiekn.demo.exception.ServiceException;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtToken {

    private final static String SECRET = "www.hiekn.com-www.plantdata.cn";

    public static String createToken() throws Exception{
        //签发时间
        Date iaDate = new Date();

        //过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,1);
        Date expireDate = nowTime.getTime();

        Map<String,Object> map = Maps.newHashMap();

        return JWT.create()
                .withHeader(map)
                .withClaim("userId",1)
                .withExpiresAt(expireDate)
                .withIssuedAt(iaDate)
                .withIssuer("hiekn")
                .sign(Algorithm.HMAC384(SECRET));

    }

    public static Map<String,Claim> checkToken(String token) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC384(SECRET)).build();
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw ServiceException.newInstance();
        }

        return jwt.getClaims();
    }


    public static void main(String[] args) throws Exception{
        String token = createToken();
        System.out.println(token);

        Map<String, Claim> claims = checkToken(token);
        claims.forEach((k,v) -> {
            System.out.println(k+"  "+v.asString());
        });

        String tokenExpire = "eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJoaWVrbiIsImV4cCI6MTUyMTE3MDIwMiwidXNlcklkIjoxLCJpYXQiOjE1MjExNzAxNDJ9.h1YFCTEVn62BiJU6qjWTEGpGOAQZCViWDuMezXLKT728u6R9TYGgvNIuTLNMGGgL";

        checkToken(tokenExpire);

    }
}
