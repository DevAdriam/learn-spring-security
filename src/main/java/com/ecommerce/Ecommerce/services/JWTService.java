package com.ecommerce.Ecommerce.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.NoSuchAlgorithmException;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private  String secretKey ="";

    public JWTService(){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey  key = keyGen.generateKey();
            secretKey =  Base64.getEncoder().encodeToString(key.getEncoded());
            System.out.println("secretKey: "+secretKey);
        }catch  (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username) {

        Map<String,Object> claims = new HashMap<>();


        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .and()
                .signWith(getSecretKey())
                .compact();

    }

    private @NotNull SecretKey getSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
       return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
      try{
          SecretKey key = getSecretKey();
          Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
          return true;
      }catch(SecurityException | MalformedJwtException e){
          throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
      }catch (ExpiredJwtException e){
          throw new AuthenticationCredentialsNotFoundException("Expired JWT token.");
      }catch (UnsupportedJwtException e){
          throw new AuthenticationCredentialsNotFoundException("Unsupported JWT token");
      }catch (IllegalArgumentException e){
          throw new AuthenticationCredentialsNotFoundException("JWT token compact of handler are invalid.");
      }
    }

    private Claims extractAllClaims(String token) {
        try{
            var parser = Jwts.parser().verifyWith(getSecretKey()).build();
            return parser.parseSignedClaims(token).getPayload();
        }catch(JwtException e){
           throw new AuthenticationCredentialsNotFoundException(e.getMessage());
        }
    }
}
