package com.pm.securitybackend;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET;

    private static final long EXPIRATION = Duration.ofHours(3).toMillis();

    private Key SECRET_KEY (){  return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));}

    public String tokenGenerate(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(SECRET_KEY(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String ExtractMailFromJwt(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean ValidateJwt(String token){
        try{
            return ExtractMailFromJwt(token) != null;
        } catch (JwtException e) {
            return false;
        }
    }
}
