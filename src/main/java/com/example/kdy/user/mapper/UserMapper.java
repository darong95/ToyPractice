package com.example.kdy.user.mapper;

import com.example.kdy.user.dto.UserDTO;
import com.example.kdy.user.dto.UserListDTO;
import com.example.kdy.user.entity.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    // Entity ➡️ DTO
    UserDTO convertToDTO(UserEntity userEntity);

    @Mapping(target = "userDTO", expression = "java(convertToDTO(userEntity))")
    UserListDTO convertToListDTO(UserEntity userEntity);

    List<UserDTO> convertToListDTO(List<UserEntity> userList);
    List<UserListDTO> convertToReadListDTO(List<UserEntity> userList);
}

