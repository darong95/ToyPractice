package com.example.kdy.board.mapper;

import com.example.kdy.board.dto.BoardDTO;
import com.example.kdy.board.entity.BoardEntity;
import com.example.kdy.user.entity.UserEntity;

public class BoardMapperManual {
    public BoardEntity convertToEntity(BoardDTO boardDTO) {
        if (boardDTO == null) {
            return null;
        }

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardSeq(boardDTO.getBoardSeq());

        if (boardDTO.getUserSeq() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserSeq(boardDTO.getUserSeq());

            boardEntity.setUserEntity(userEntity);
        }

        // 필요한 나머지 필드 매핑
        return boardEntity;
    }
}
