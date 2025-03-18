package com.estoqueige.estoqueige.security;

import java.util.Date;
import java.util.Objects;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfigurationSource;
import com.estoqueige.estoqueige.EstoqueIgeApplication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTutil {

    private final EstoqueIgeApplication estoqueIgeApplication;

    private final CorsConfigurationSource corsConfigurationSource;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    JWTutil(BCryptPasswordEncoder bCryptPasswordEncoder, CorsConfigurationSource corsConfigurationSource, EstoqueIgeApplication estoqueIgeApplication) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.corsConfigurationSource = corsConfigurationSource;
        this.estoqueIgeApplication = estoqueIgeApplication;
    }

    public String generateToken(String userName){
        SecretKey key = getKeyBySecret();
        return Jwts.builder()
            .setSubject(userName)
            .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
            .signWith(key)
            .compact();
    }

    private SecretKey getKeyBySecret(){
        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());
        return key;
    }

    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);
        if (Objects.nonNull(claims)) {
            String userName = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
    
            if (Objects.nonNull(userName) && Objects.nonNull(expirationDate) && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token){
        SecretKey key = getKeyBySecret();
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
