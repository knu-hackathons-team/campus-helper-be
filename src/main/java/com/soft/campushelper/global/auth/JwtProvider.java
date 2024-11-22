package com.soft.campushelper.global.auth;

import com.soft.campushelper.member.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration-time}")
    private Long expirationTime;

    public String createToken(Long id, Role role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim("role", role.name())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims getClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (SecurityException | MalformedJwtException e) {
            throw new JwtException("유효하지 않은 토큰입니다.");
        } catch (ExpiredJwtException e) {
            throw new JwtException("만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("지원되지 않는 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new JwtException("토큰이 존재하지 않습니다.");
        }
    }
}
