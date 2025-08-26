package com.example.kdy.board;

import com.example.kdy.board.entity.BoardEntity;
import com.example.kdy.board.repository.BoardRepository;
import com.example.kdy.common.config.DynamicPropertiesSource;
import com.example.kdy.user.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BoardDummyWriteTest extends DynamicPropertiesSource {
    @Autowired
    BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    @Rollback(value = false) // DB에 데이터 남겨놓기
    void boardDummyWrite() {
        List<BoardEntity> boardDummyList = new ArrayList<>();
        UserEntity userEntity = entityManager.getReference(UserEntity.class, 1L);

        for (int i = 0; i < 25; i++) {
            BoardEntity boardEntity = new BoardEntity();

            boardEntity.setBoardRegId("admin");
            boardEntity.setUserEntity(userEntity);
            boardEntity.setBoardTitle("[TEST] 테스트용 게시글입니다.");
            boardEntity.setBoardContent("[TEST] 테스트용 게시글입니다.");

            boardDummyList.add(boardEntity);
        }

        boardRepository.saveAll(boardDummyList);
    }
}
