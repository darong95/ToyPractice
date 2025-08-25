package com.example.kdy.user.service;

import com.example.kdy.user.dto.UserListDTO;
import com.example.kdy.user.entity.UserEntity;
import com.example.kdy.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Page<UserEntity> userList(UserListDTO userListDTO) { // 사용자 리스트
        int currentPage = userListDTO.getCurrentPage();
        int pagingSize = userListDTO.getPagingSize();

        Pageable pageable = PageRequest.of(currentPage, pagingSize, Sort.by("userSeq").descending()); // DESC

        return userRepository.findAll(pageable);
    }
}
