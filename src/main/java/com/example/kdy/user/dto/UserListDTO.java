package com.example.kdy.user.dto;

import com.example.kdy.common.dto.PagingDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListDTO extends PagingDTO {
    private UserDTO userDTO;
}
