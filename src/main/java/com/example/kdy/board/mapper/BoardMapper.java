package com.example.kdy.board.mapper;

import com.example.kdy.board.dto.BoardDTO;
import com.example.kdy.board.dto.BoardListDTO;
import com.example.kdy.board.dto.BoardUpdateDTO;

import com.example.kdy.board.dto.BoardWriteDTO;
import com.example.kdy.board.entity.BoardEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
    // Entity ➡️ DTO
    @Mapping(source = "userEntity.userSeq", target = "userSeq")
    BoardDTO convertToDTO(BoardEntity boardEntity);

    @Mapping(target = "boardDTO", expression = "java(convertToDTO(boardEntity))")
    BoardListDTO convertToListDTO(BoardEntity boardEntity);

    @Mapping(target = "boardDTO", expression = "java(convertToDTO(boardEntity))")
    BoardUpdateDTO convertToUpdateDTO(BoardEntity boardEntity);

    List<BoardListDTO> convertToLReadListDTO(List<BoardEntity> boardList);

    // DTO ➡️ Entity
    @Mapping(source = "userSeq", target = "userEntity.userSeq")
    BoardEntity convertToEntity(BoardDTO boardDTO);

    @Mapping(source = "boardDTO", target = ".") // BoardDTO 매칭해서 Mapper
    @Mapping(source = "boardDTO.userSeq", target = "userEntity.userSeq")
    BoardEntity convertToWriteEntity(BoardWriteDTO boardWriteDTO);
}
