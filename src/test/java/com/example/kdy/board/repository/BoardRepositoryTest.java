package com.example.kdy.board.repository;

import com.example.kdy.board.entity.BoardEntity;
import com.example.kdy.user.entity.UserEntity;
import com.example.kdy.user.repository.UserRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class BoardRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void boardCreateAndRead() {
        // User Setting
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId("admin");
        userEntity.setUserPassword("1234");
        userEntity.setUserName("관리자");
        userEntity.setUserEmail("test@gmail.com");
        userEntity.setUserPhoneNumber("01012345678");

        userRepository.save(userEntity);

        // Board Setting
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setUserEntity(userEntity);
        boardEntity.setBoardRegId("admin");
        boardEntity.setBoardTitle("[JUnit] JUnit Test");
        boardEntity.setBoardContent("JUnit으로 작성된 게시글입니다.");

        // Create
        BoardEntity createBoardEntity = boardRepository.save(boardEntity);

        // Read
        Optional<BoardEntity> optional = boardRepository.findById(createBoardEntity.getBoardSeq());

        Assertions.assertThat(optional).isPresent();
        Assertions.assertThat(optional.get().getBoardTitle()).isEqualTo("[JUnit] JUnit Test");
    }
}
