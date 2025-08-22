package com.example.kdy.mapper.board;

import com.example.kdy.dto.board.BoardDTO;
import com.example.kdy.entity.board.BoardEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    @Mapping(source = "userEntity.USeq", target = "USeq")
    BoardDTO convertToDTO(BoardEntity boardEntity);
    List<BoardDTO> convertToListDTO(List<BoardEntity> boardList);

    @Mapping(source = "USeq", target = "userEntity.USeq")
    BoardEntity convertToEntity(BoardDTO boardDTO);
}
