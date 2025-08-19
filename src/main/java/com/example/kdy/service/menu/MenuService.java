package com.example.kdy.service.menu;

import com.example.kdy.entity.menu.MenuEntity;
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
    private final MenuRepository menuRepository;

    public List<MenuEntity> menuList() { // 메뉴 리스트
        Sort sort = Sort.by(Sort.Order.asc("mParentSeq"), Sort.Order.asc("mDepthOrder"));
        return menuRepository.findAll(sort);
    }
}
