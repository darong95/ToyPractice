package com.example.kdy.mapper.menu;


import com.example.kdy.dto.menu.MenuDTO;
import com.example.kdy.entity.menu.MenuEntity;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuDTO convertToDTO(MenuEntity menuEntity);
    List<MenuDTO> convertToListDTO(List<MenuEntity> menuList);
}
