package com.example.kdy.board.mapper;

import com.example.kdy.board.dto.BoardDTO;
import com.example.kdy.board.dto.BoardListDTO;
import com.example.kdy.board.entity.BoardEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    @Mapping(source = "userEntity.USeq", target = "USeq")
    BoardDTO convertToDTO(BoardEntity boardEntity);

    @Mapping(target = "boardDTO", expression = "java(convertToDTO(boardEntity))")
    BoardListDTO convertToListDTO(BoardEntity boardEntity);

    List<BoardDTO> convertToReadDTO(List<BoardEntity> boardList);
    List<BoardListDTO> convertToLReadListDTO(List<BoardEntity> boardList);

    @Mapping(source = "USeq", target = "userEntity.USeq")
    BoardEntity convertToEntity(BoardDTO boardDTO);
}
