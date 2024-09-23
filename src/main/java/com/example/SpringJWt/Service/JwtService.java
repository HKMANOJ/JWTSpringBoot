package com.example.SpringJWt.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import static io.jsonwebtoken.impl.crypto.MacProvider.generateKey;
import static java.util.Base64.*;

@Component
public class JwtService {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String generatetoken(String username){
        Map<String, Object> clims=new HashMap<>();
        return createToken(clims,username);

    }
    public <T> T extractClims(String token, Function<Claims,T> climsResolver){
        final Claims clims=extractAllClims(token);
        return climsResolver.apply(clims);
    }

    public String extractusername(String token){
        return extractClims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClims(token,Claims::getExpiration);
    }

    public  boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean isvalidToken(String token ,UserDetails  userDetails)
    {
        final String username=extractusername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(generateSigenkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String createToken(Map<String, Object> clims, String username) {

        return Jwts.builder()
                .setClaims(clims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(generateSigenkey(), SignatureAlgorithm.HS256).compact();
    }

    private Key generateSigenkey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
