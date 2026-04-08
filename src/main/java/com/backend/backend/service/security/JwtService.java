package com.backend.backend.service.security;

import com.backend.backend.dao.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access.duration-ms}")
    private long accessDurationMs;

    @Value("${jwt.refresh.duration-ms}")
    private long refreshDurationMs;

    public String generateAccessToken(User user) {
        return generateToken(user, accessDurationMs, "access");
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, refreshDurationMs, "refresh");
    }

    public boolean isValidAccessToken(String token) {
        return validateToken(token, "access");
    }

    public boolean isValidRefreshToken(String token) {
        return validateToken(token, "refresh");
    }

    public String extractSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        Object role = extractAllClaims(token).get("role");
        return role == null ? "USER" : role.toString();
    }

    private String generateToken(User user, long durationMs, String tokenType) {
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(durationMs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("role", normalizeRole(user.getRole()));
        claims.put("tokenType", tokenType);
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean validateToken(String token, String expectedTokenType) {
        try {
            Claims claims = extractAllClaims(token);
            Object tokenType = claims.get("tokenType");
            return expectedTokenType.equals(tokenType);
        } catch (Exception ex) {
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String normalizeRole(String role) {
        if (role == null || role.isBlank()) {
            return "USER";
        }
        return role.toUpperCase(Locale.ROOT);
    }
}
