package com.anjaniy.funfactsbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expiration-time}")
    private String expirationTime;

    public String extractUserEmail(String jwtToken) {
       return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(jwtToken)
            .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateJwtToken(String jwtToken, UserDetails userDetails) {
        String email = extractUserEmail(jwtToken);
        return ((userDetails.getUsername().equals(email)) && (!isJwtTokenExpired(jwtToken)));
    }

    private boolean isJwtTokenExpired(String jwtToken) {
        return extractExpirationTimeFromJwtToken(jwtToken).before(new Date());
    }

    private Date extractExpirationTimeFromJwtToken(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

}
