package com.crud.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    // store it in here it is just a demo
    private final String SECRET = "this-is-a-very-secret-key-with-exactly-32-characters!!";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public  String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 86400000))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean isTokenValid(String token){
        try{
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
