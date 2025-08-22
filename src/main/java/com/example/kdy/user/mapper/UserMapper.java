package com.example.kdy.user.mapper;

import com.example.kdy.user.dto.UserDTO;
import com.example.kdy.user.entity.UserEntity;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO convertToDTO(UserEntity userEntity);
    List<UserDTO> convertToListDTO(List<UserEntity> userList);
}

