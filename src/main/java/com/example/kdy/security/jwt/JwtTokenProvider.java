package com.example.kdy.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProvider {
    private final Key key;
    private final long expirationTime; // 보통 3600000 (1시간)

    // 생성자에서 값 초기화
    public JwtTokenProvider(
            @Value("${jwt.secret-key}") String secretKey, @Value("${jwt.expiration-time}") long expirationTime) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes()); // Secret Key 적용
        this.expirationTime = expirationTime; // 만료 시간 설정
    }

    public String generateToken(Long userSeq, String userId) { // JWT 생성 (JWT는 Header.Payload.Signature로 나뉨)
        return Jwts.builder()
                .setSubject(String.valueOf(userSeq))
                .claim("userId", userId)
                .setIssuedAt(new Date()) // 토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 토큰 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 서명 (HMAC SHA-256 알고리즘 사용)
                .compact(); // 최종적으로 JWT 문자열 생성
    }

    public boolean validateToken(String jwtToken) { // Token 유효성 검증
        try {
            getClaims(jwtToken);
            return true; // 파싱 된다면 서명 + 만료 검증 완료

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long extractUserSeq(String jwtToken) { // Subject Extract
        Claims _tempTokenClaims = getClaims(jwtToken);
        return Long.valueOf(_tempTokenClaims.getSubject());
    }

    public String extractUserId(String jwtToken) { // UserId Extract (Claim)
        Claims _tempTokenClaims = getClaims(jwtToken);
        return String.valueOf(_tempTokenClaims.get("userId"));
    }

    private Claims getClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key).build() // Token 검증할 때 사용할 서명 Key 지정
                .parseClaimsJws(jwtToken) // JWT 검증 + 서명 검증 : 유효하지 않을 경우 예외 발생
                .getBody(); // JWT의 Payload(Claims) 부분 추출 ➡️ Subject 가져오기 (UserSeq)
    }
}
