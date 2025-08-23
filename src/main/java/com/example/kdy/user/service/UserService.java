package com.example.kdy.user.service;

import com.example.kdy.user.dto.UserListDTO;
import com.example.kdy.user.entity.UserEntity;
import com.example.kdy.user.mapper.UserMapper;
import com.example.kdy.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserListDTO> userList() { // 사용자 리스트
        Sort sort = Sort.by(Sort.Order.desc("USeq"));
        List<UserEntity> userList = userRepository.findAll(sort);

        return userMapper.convertToReadListDTO(userList);
    }
}
