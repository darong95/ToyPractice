package com.example.kdy.menu.service;

import com.example.kdy.menu.dto.MenuDTO;
import com.example.kdy.menu.entity.MenuEntity;
import com.example.kdy.menu.mapper.MenuMapper;
import com.example.kdy.menu.repository.MenuRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor // 생성자 자동 주입
public class MenuService {
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public List<MenuDTO> menuList() { // 메뉴 리스트
        Sort sort = Sort.by(Sort.Order.asc("menuParentSeq"), Sort.Order.asc("menuDepthOrder")); // 정렬 조건 설정
        List<MenuEntity> menuList = menuRepository.findAll(sort); // 메뉴 리스트 가져오기

        return menuMapper.convertToListDTO(menuList); // Entity를 DTO에 변환 시키며 리턴
    }

    @Transactional(readOnly = true)
    public List<MenuDTO> menuRolesList(List<String> userRoles) { // 메뉴 리스트
        List<MenuEntity> menuList = menuRepository.findByMenuRole(userRoles); // 메뉴 리스트 가져오기
        return menuMapper.convertToListDTO(menuList); // Entity를 DTO에 변환 시키며 리턴
    }
}
