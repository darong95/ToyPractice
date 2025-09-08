package com.example.kdy.common.db;

import com.example.kdy.common.db.entity.ExampleEntity;
import com.example.kdy.common.db.repository.ExampleRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test") // application-test.yml
public class ExampleDBRunTest { // DB 생성 + 테스트 데이터 Create
    @Autowired
    private ExampleRepository exampleRepository;

    @Test
    public void dbConnection_H2() {
        ExampleEntity exampleEntity = new ExampleEntity();

        exampleEntity.setUserName("DaYoung");
        exampleEntity.setUserAge("ThirtyOne");

        exampleRepository.save(exampleEntity);

        ExampleEntity exampleEntity_Find = exampleRepository.findById(exampleEntity.getUserNum()).orElse(null);

        Assertions.assertThat(exampleEntity_Find).isNotNull();
        Assertions.assertThat(exampleEntity_Find.getUserName()).isEqualTo("DaYoung");
    }
}
