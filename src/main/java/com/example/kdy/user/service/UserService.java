package com.example.kdy.user.service;

import com.example.kdy.common.component.AsteriskUtilComponent;
import com.example.kdy.common.component.DateUtilComponent;
import com.example.kdy.common.component.IF.AccessorNameInterface;

import com.example.kdy.common.component.ListDateFormatComponent;
import com.example.kdy.user.dto.UserListDTO;
import com.example.kdy.user.entity.UserEntity;
import com.example.kdy.user.mapper.UserMapper;
import com.example.kdy.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiConsumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final AsteriskUtilComponent asteriskUtilComponent;
    private final ListDateFormatComponent listDateFormatComponent;
    private final DateUtilComponent dateUtilComponent;

    public List<UserListDTO> userList() { // 사용자 리스트
        Sort sort = Sort.by(Sort.Order.desc("USeq"));

        List<UserEntity> userList = userRepository.findAll(sort);
        List<UserListDTO> userListDTO = userMapper.convertToReadListDTO(userList);

        if (!userListDTO.isEmpty()) {
            // 사용자 이름 Asterisk
            asteriskUtilComponent.asterisk_Name_List(userListDTO, new AccessorNameInterface<UserListDTO>() {
                @Override
                public String getAccessName(UserListDTO userListDTO) {
                    return userListDTO.getUserDTO().getUName();
                }

                @Override
                public void setAccessName(UserListDTO userListDTO, String settingName) {
                    userListDTO.getUserDTO().setUName(settingName);
                }
            });

            // 날짜 출력 Format
            listDateFormatComponent.forListDate(userListDTO, new BiConsumer<UserListDTO, DateUtilComponent>() {
                @Override
                public void accept(UserListDTO boardListDTO, DateUtilComponent dateUtilComponent) { // BiConsumer Override
                    String tempDate = dateUtilComponent.format_String(boardListDTO.getUserDTO().getRegDate(), "yyyyMMdd");
                    boardListDTO.getUserDTO().setRegDate(tempDate); // 포맷한 데이터 다시 넣기
                }

            }, dateUtilComponent);
        }

        return userListDTO;
    }
}
