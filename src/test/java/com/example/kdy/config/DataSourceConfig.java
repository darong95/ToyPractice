package com.example.kdy.config;

import com.example.kdy.EnvValueSettingTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@TestConfiguration // JUnit Configuration
public class DataSourceConfig {
    @Primary // DataSource Default
    @Bean(name="configMariaDB")
    @ConfigurationProperties("spring.datasource.mariadb")
    public DataSource source_MariaDB() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="configMariaH2")
    @ConfigurationProperties("spring.datasource.h2")
    public DataSource source_H2() {
        return DataSourceBuilder.create().build();
    }
}
