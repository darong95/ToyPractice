package com.example.kdy.mapper.user;

import com.example.kdy.dto.user.UserDTO;
import com.example.kdy.entity.user.UserEntity;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO convertToDTO(UserEntity userEntity);
    List<UserDTO> convertToListDTO(List<UserEntity> userList);
}

