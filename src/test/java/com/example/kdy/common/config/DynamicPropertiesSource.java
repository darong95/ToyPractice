package com.example.kdy.common.config;


import com.example.kdy.common.env.EnvValueLoad;
import org.junit.jupiter.api.TestInstance;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DynamicPropertiesSource {
    @DynamicPropertySource
    static void dynamicEnvProperty(DynamicPropertyRegistry dynamicPropertyRegistry) {
        Properties properties = EnvValueLoad.envPropertiesLoad("src/main/resources/local.env");

        String dataSource_URL = EnvValueLoad.envProperty_GitHub(properties, "SPRING_DATASOURCE_H2_URL");
        String dataSource_UserName = EnvValueLoad.envProperty_GitHub(properties, "SPRING_DATASOURCE_H2_USERNAME");
        String dataSource_Password = EnvValueLoad.envProperty_GitHub(properties, "SPRING_DATASOURCE_H2_PASSWORD");
        String dataSource_DriverClassName = "org.h2.Driver"; // 직접 입력, 추후 Property 값으로 변경

        dynamicPropertyRegistry.add("spring.datasource.url", () -> dataSource_URL);
        dynamicPropertyRegistry.add("spring.datasource.username", () -> dataSource_UserName);
        dynamicPropertyRegistry.add("spring.datasource.password", () -> dataSource_Password);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", () -> dataSource_DriverClassName);
    }
}
