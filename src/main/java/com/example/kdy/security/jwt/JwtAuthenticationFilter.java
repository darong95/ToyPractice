package com.example.kdy.security.jwt;

import com.example.kdy.security.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Value("${jwt.cookie-name}")
    private String jwtCookieName;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = null;
        String whiteURI = httpServletRequest.getRequestURI();

        // SecurityConfig PermitAll ➡️ White URI
        if (whiteURI.startsWith("/api/")
                || whiteURI.startsWith("/auth/")
                || whiteURI.startsWith("/css/")
                || whiteURI.startsWith("/js/")
                || whiteURI.startsWith("/img/")
                || whiteURI.startsWith("/vendor/")
                || whiteURI.startsWith("/scss/")
                || whiteURI.startsWith("/favicon.ico")) {

            filterChain.doFilter(httpServletRequest, httpServletResponse);

            return;
        }

        // JWT Token ➡️ Authorization 방식
        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7); // JWT Token
        }

        // JWT Token ➡️ HttpOnly 방식 (Cookie)
        if (jwtToken == null && httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (jwtCookieName.equals(cookie.getName())) { // 쿠키 이름은 네가 지정한 값
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        }

        // JWT Token 유효성 검사
        if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)) {
            Long userSeq = jwtTokenProvider.extractUserSeq(jwtToken);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(String.valueOf(userSeq));

            // Spring Security 인증 객체 생성
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    ); // JWT 인증이므로 가운데 값은 Null

            // 요청 정보 (HttpRequest)를 Authentication에 저장
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            // SecurityContextHolder에 인증 객체 저장 ➡️ JWT 인증 완료
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // 다음 Filter로 Request, Response 전달
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
