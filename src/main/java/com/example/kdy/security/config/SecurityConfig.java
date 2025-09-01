package com.example.kdy.security.config;

import com.example.kdy.security.jwt.JwtAccessDeniedHandler;
import com.example.kdy.security.jwt.JwtAuthenticationEntryPoint;
import com.example.kdy.security.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter; // JWT 인증 Filter
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler; // 403 인가 실패 처리기
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint; // 401 인증 실패 처리기

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화 (Session / Form 기반이 아님)
                .formLogin(AbstractHttpConfigurer::disable) // 자체 로그인 페이지 미사용 (React 같은 환경) ➡️ Session 기반 로그인 아님
                .httpBasic(AbstractHttpConfigurer::disable) // Header로 Token만 사용
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 전역 Filter 설정

                // 접근 권한 설정
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/auth/**", "/api/**", "/css/**", "/js/**", "/img/**", "/vendor/**", "/scss/**", "/favicon.ico").permitAll() // 인증 없이 접근 허용
                        .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                )

                // 예외 처리 (Exception)
                .exceptionHandling(exceptionHandler -> exceptionHandler
                        .accessDeniedHandler(jwtAccessDeniedHandler) // 403 (인가 로직)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 401 (인증 로직)
                )

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // 인증 Filter
        
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // CORS 규칙
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(true); // 자격 증명 (Cookie, Authorization Header) 허용
        corsConfiguration.setAllowedOriginPatterns(List.of("*")); // 요청 Origin 패턴 허용 (와일드카드, 실제 응답 시 Origin 에코)
        corsConfiguration.setAllowedHeaders(List.of("*")); // 모든 요청 Header 허용
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); // 요청 메소드 지정

        // URL 패턴별 CORS 설정 매핑
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration); // 모든 경로에 대해 위 CORS 규칙 적용

        return urlBasedCorsConfigurationSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt 해시 사용(권장)
    }
}
