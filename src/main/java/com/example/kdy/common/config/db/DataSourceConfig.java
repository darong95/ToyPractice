package com.example.kdy.common.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
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
