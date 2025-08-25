package com.example.kdy.board.mapper;

import com.example.kdy.board.dto.BoardFileDTO;
import com.example.kdy.board.entity.BoardFileEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardFileMapper {
    // Entity ➡️ DTO
    @Mapping(source = "boardEntity.boardSeq", target = "boardSeq")
    BoardFileDTO convertToDTO(BoardFileEntity boardFileEntity);
    List<BoardFileDTO> convertToReadListDTO(List<BoardFileEntity> boardFileList);
}
