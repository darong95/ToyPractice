package com.example.kdy.service.menu;

import com.example.kdy.dto.menu.MenuDTO;
import com.example.kdy.entity.menu.MenuEntity;
import com.example.kdy.mapper.menu.MenuMapper;
import com.example.kdy.repository.menu.MenuRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor // 생성자 자동 주입
public class MenuService {
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;

    public List<MenuDTO> menuList() { // 메뉴 리스트
        Sort sort = Sort.by(Sort.Order.asc("mParentSeq"), Sort.Order.asc("mDepthOrder")); // 정렬 조건 설정
        List<MenuEntity> menuList = menuRepository.findAll(sort); // 메뉴 리스트 가져오기

        return menuMapper.convertToListDTO(menuList); // Entity를 DTO에 변환 시키며 리턴
    }
}
