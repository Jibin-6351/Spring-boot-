package com.example.demo.Utils;

import com.example.demo.domain.Token;
import com.example.demo.domain.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {

    @Autowired
    private TokenRepository tokenRepository;
    private static final String SECRET_KEY = "jI9pA2JdVbZ6df8qWz8S4fP6LpYKx8i4I7sR9+U/E4A=";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;   // One hour
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        String email = this.extractUsername(token);
        Optional<Token> token1 = tokenRepository.findByUserEmail(email);
        try {
            if (token1.isPresent()) {
                Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
                return true;
            }
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
            return false;
        }
        return false;
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
