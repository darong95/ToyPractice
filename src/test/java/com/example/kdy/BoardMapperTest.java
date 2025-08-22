package com.example.kdy;


import com.example.kdy.common.DynamicPropertiesSource;
import com.example.kdy.dto.board.BoardDTO;
import com.example.kdy.entity.board.BoardEntity;

import com.example.kdy.mapper.BoardMapperManual;
import com.example.kdy.mapper.board.BoardMapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

public class BoardMapperTest extends DynamicPropertiesSource {
    @Autowired
    BoardMapper boardMapper;

    @Test
    void convertToEntity() { // DTO를 Entity로 변환
        BoardDTO boardDTO = new BoardDTO();
        BoardMapperManual boardMapperManual = new BoardMapperManual();

        // 필수 값 Setting
        boardDTO.setBSeq(1L);
        boardDTO.setUSeq(1L);
        boardDTO.setBRegId("admin");
        boardDTO.setBTitle("JUnit5 TEST");
        boardDTO.setBContent("DTO Convert To Entity");

        // Entity로 Convert
        BoardEntity boardEntity = boardMapper.convertToEntity(boardDTO);
        // BoardEntity boardEntity = boardMapperManual.convertToEntity(boardDTO); // 직접 임의로 만들어줌
        
        // 변환 결과 확인
        Assertions.assertThat(boardEntity).isNotNull();
        Assertions.assertThat(boardEntity.getBSeq()).isEqualTo(1L);

        Assertions.assertThat(boardEntity.getUserEntity()).isNotNull();
        Assertions.assertThat(boardEntity.getUserEntity().getUSeq()).isEqualTo(1L);
    }
}
