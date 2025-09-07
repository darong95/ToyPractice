package com.example.kdy.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "DaRong's BoardHub API", version = "v1"))
@SecurityScheme(
        name = "SwaggerAuth", // 보안 설정 이름
        type = SecuritySchemeType.HTTP, // 보안 인증 방식 ➡️ Http
        bearerFormat = "JWT", // 토큰 포맷 ➡️ JWT
        scheme = "Bearer", // 인증 Schema
        in = SecuritySchemeIn.HEADER, // Schema 위치 ➡️ Http Header
        description = "JWT 토큰을 여기에 입력하세요. (ex. Bearer <token>)" // 사용자에게 보여줄 설명
)
public class SwaggerApiConfig {
}
