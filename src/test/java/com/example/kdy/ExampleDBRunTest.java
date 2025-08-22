package com.example.kdy;

import com.example.kdy.common.DynamicPropertiesSource;
import com.example.kdy.entity.ExampleEntity;
import com.example.kdy.repository.ExampleRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback(value = false)
public class ExampleDBRunTest extends DynamicPropertiesSource {
    @Autowired
    private ExampleRepository exampleRepository;

    @Test
    public void dbConnection_MariaDB() {
        ExampleEntity exampleEntity = new ExampleEntity();

        exampleEntity.setUserName("DaYoung");
        exampleEntity.setUserAge("ThirtyOne");

        exampleRepository.save(exampleEntity);

        ExampleEntity exampleEntity_Find = exampleRepository.findById(exampleEntity.getUserNum()).orElse(null);

        Assertions.assertThat(exampleEntity_Find).isNotNull();
        Assertions.assertThat(exampleEntity_Find.getUserName()).isEqualTo("DaYoung");
    }
}
