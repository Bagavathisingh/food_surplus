package com.pm.api_gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET;

    private Claims ExtractClaims(String Token){
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(Token)
                .getPayload();
    }

    public String ExtractUSer_id(String token){
        return ExtractClaims(token)
                .get("user_id", String.class);
    }

    public String ExtractRole(String token){
        return ExtractClaims(token)
                .get("role",String.class);
    }


    public boolean ValidateToken(String token){
        try {
            ExtractClaims(token);
            return true;
        }
        catch (JwtException | IllegalArgumentException IllAr){
            return false;
        }
    }
}
