package com.example.kdy.board.mapper;

import com.example.kdy.board.dto.BoardDTO;
import com.example.kdy.board.entity.BoardEntity;

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
