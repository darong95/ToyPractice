package com.example.kdy.board.mapper;

import com.example.kdy.board.dto.BoardDTO;
import com.example.kdy.board.dto.BoardListDTO;
import com.example.kdy.board.dto.BoardUpdateDTO;

import com.example.kdy.board.dto.BoardWriteDTO;
import com.example.kdy.board.entity.BoardEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    // Entity ➡️ DTO
    @Mapping(source = "userEntity.userSeq", target = "userSeq")
    BoardDTO convertToDTO(BoardEntity boardEntity);

    @Mapping(target = "currentPage", ignore = true)
    @Mapping(target = "pagingSize", ignore = true)
    @Mapping(target = "boardDTO", expression = "java(convertToDTO(boardEntity))")
    BoardListDTO convertToListDTO(BoardEntity boardEntity);

    @Mapping(target = "boardFileUpdateList", ignore = true)
    @Mapping(target = "boardFileDeleteList", ignore = true)
    @Mapping(target = "boardDTO", expression = "java(convertToDTO(boardEntity))")
    BoardUpdateDTO convertToUpdateDTO(BoardEntity boardEntity);

    List<BoardListDTO> convertToLReadListDTO(List<BoardEntity> boardList);

    // DTO ➡️ Entity
    @Mapping(source = "userSeq", target = "userEntity.userSeq")
    BoardEntity convertToEntity(BoardDTO boardDTO);

    @Mapping(target = "regDate", ignore = true)
    @Mapping(source = "boardDTO", target = ".") // BoardDTO 매칭해서 Mapper
    @Mapping(source = "boardDTO.userSeq", target = "userEntity.userSeq")
    @Mapping(target = "userEntity.userId", ignore = true)
    @Mapping(target = "userEntity.userName", ignore = true)
    @Mapping(target = "userEntity.userPassword", ignore = true)
    @Mapping(target = "userEntity.userEmail", ignore = true)
    @Mapping(target = "userEntity.userPhoneNumber", ignore = true)
    @Mapping(target = "userEntity.userRoles", ignore = true)
    BoardEntity convertToWriteEntity(BoardWriteDTO boardWriteDTO);

    /*
    @Mapping(target = "regDate", ignore = true)
    @Mapping(target = "userEntity", ignore = true)
    BoardEntity convertToUpdateEntity(BoardUpdateDTO boardDUpdateTO);
    */
}
