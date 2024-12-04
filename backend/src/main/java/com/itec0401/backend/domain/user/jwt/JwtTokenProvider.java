package com.itec0401.backend.domain.user.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final Long accessTokenValidTime = Duration.ofHours(24).toMillis();
    private static final Long refreshTokenValidTime = Duration.ofDays(7).toMillis();

    @Value("${JWT.SECRET-KEY}")
    private String secretKey;


    public String getEmail(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
    }

    public boolean isAccessToken(String token) throws MalformedJwtException {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getHeader().get("type").toString().equals("access");
    }

    public String createAccessToken(String email){
        return createJwtToken(email, secretKey, "access", accessTokenValidTime);
    }

    public String createRefreshToken(String email){
        return createJwtToken(email, secretKey, "refresh", refreshTokenValidTime);
    }

    private String createJwtToken(String email, String secretKey, String type, Long tokenValidTime){
        Claims claims = Jwts.claims();
        claims.put("email", email);

        return Jwts.builder()
                .setHeaderParam("type", type)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
