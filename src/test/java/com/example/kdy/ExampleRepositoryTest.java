package com.example.kdy;

import com.example.kdy.config.env.EnvValueLoad;
import com.example.kdy.entity.ExampleEntity;
import com.example.kdy.repository.ExampleRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.springframework.transaction.annotation.Transactional;

import java.util.Properties;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class ExampleRepositoryTest {
    @Autowired
    private ExampleRepository exampleRepository;

    @DynamicPropertySource
    static void dynamicEnvProperty(DynamicPropertyRegistry dynamicPropertyRegistry) {
        Properties properties = EnvValueLoad.envPropertiesLoad("src/main/resources/local.env");

        String dataSource_URL = EnvValueLoad.envProperty_GitHub(properties, "SPRING_DATASOURCE_MARIADB_URL");
        String dataSource_UserName = EnvValueLoad.envProperty_GitHub(properties, "SPRING_DATASOURCE_MARIADB_USERNAME");
        String dataSource_Password = EnvValueLoad.envProperty_GitHub(properties, "SPRING_DATASOURCE_MARIADB_PASSWORD");
        String dataSource_DriverClassName = "org.mariadb.jdbc.Driver"; // 직접 입력, 추후 Property 값으로 변경

        /*
        System.out.println("[TEST] JUNIT URL :: " + dataSource_URL);
        System.out.println("[TEST] JUNIT USERNAME :: " + dataSource_UserName);
        System.out.println("[TEST] JUNIT PASSWORD :: " + dataSource_Password);
        */

        dynamicPropertyRegistry.add("spring.datasource.mariadb.jdbc-url", () -> dataSource_URL);
        dynamicPropertyRegistry.add("spring.datasource.mariadb.username", () -> dataSource_UserName);
        dynamicPropertyRegistry.add("spring.datasource.mariadb.password", () -> dataSource_Password);
        dynamicPropertyRegistry.add("spring.datasource.mariadb.driver-class-name", () -> dataSource_DriverClassName);
    }

    @Test
    public void dbConnection_MariaDB() {
        ExampleEntity exampleEntity = new ExampleEntity();

        exampleEntity.setUserName("Da Young");
        exampleEntity.setUserAge("Thirty One");

        exampleRepository.save(exampleEntity);

        ExampleEntity exampleEntity_Find = exampleRepository.findById(exampleEntity.getUserNum()).orElse(null);

        Assertions.assertThat(exampleEntity_Find).isNotNull();
        Assertions.assertThat(exampleEntity_Find.getUserName()).isEqualTo("Da Young");
    }
}
