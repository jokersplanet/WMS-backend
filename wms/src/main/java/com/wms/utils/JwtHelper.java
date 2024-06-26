package com.wms.utils;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * jwt工具类
 * @author lwh
 * @create 2024-06-24 23:51
 */
public class JwtHelper {

    private static long tokenExpiration = 365 * 24 * 60 * 60 * 1000;

    private static String tokenSignKey = "123456";

    //根据用户id和用户名称生成token字符串
    public static String createToken(Long userId, String userAccount){
        String token = Jwts.builder()
                //分类
                .setSubject("AUTH-USER")
                //设置token有效时长
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                //设置主题部分
                .claim("uid",userId)
                .claim("user_account",userAccount)
                //签名部分
                .signWith(SignatureAlgorithm.HS512,tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //从生成的token字符串中获取用户id
    public static Long getUserId(String token){
        try {
            if (StringUtils.isEmpty(token)) return null;
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("uid");
            return userId.longValue();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //从生成的token字符串中获取用户名称
    public static String getUserAccount(String token){
        try {
            if(StringUtils.isEmpty(token)) return "";
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("user_account");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        String token = JwtHelper.createToken(1L, "LUCY");
        System.out.println(token);
        Long userId = JwtHelper.getUserId(token);
        String userAccount = JwtHelper.getUserAccount(token);
        System.out.println(userId);
        System.out.println(userAccount);
    }
}
