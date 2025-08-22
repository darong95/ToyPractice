package com.example.kdy.mapper;

import com.example.kdy.dto.board.BoardDTO;
import com.example.kdy.entity.board.BoardEntity;
import com.example.kdy.entity.user.UserEntity;

public class BoardMapperManual {
    public BoardEntity convertToEntity(BoardDTO boardDTO) {
        if (boardDTO == null) {
            return null;
        }

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBSeq(boardDTO.getBSeq());

        if (boardDTO.getUSeq() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUSeq(boardDTO.getUSeq());

            boardEntity.setUserEntity(userEntity);
        }

        // 필요한 나머지 필드 매핑
        return boardEntity;
    }
}
