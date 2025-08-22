package com.example.kdy.menu.mapper;


import com.example.kdy.menu.dto.MenuDTO;
import com.example.kdy.menu.entity.MenuEntity;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuDTO convertToDTO(MenuEntity menuEntity);
    List<MenuDTO> convertToListDTO(List<MenuEntity> menuList);
}
