package com.medilabo.diagnosis_patients.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utilty class fournissant les méthodes nécessaires à la manipulation du JWT : validation,
 * extraction des claims, vérification de l'expiration du token et generation d'un objet
 * Authentication à partir du JWT.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET;

    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Extraction et validation du token (verification de son authenticité par sa signature)
    public Claims extractClaims(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims;
    }

    public String extractUsername(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    public String extractAuthorities(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    public boolean isTokenExpired(String token) {
        Claims claims = extractClaims(token);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }

    // Creation d'un objet Authentication à partir du JWT token en renvoyant un UsernamePasswordAuthenticationToken
    public Authentication getAuthentication(String token) {
        Claims claims = extractClaims(token);
        String username = claims.getSubject();
        String roles = claims.get("roles", String.class);

        List<GrantedAuthority> authorities = Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //Credentials null - protection du password
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

}
