package com.example.kdy.board.controller;

import com.example.kdy.board.dto.BoardDTO;
import com.example.kdy.board.dto.BoardWriteDTO;
import com.example.kdy.common.env.EnvValueLoad;
import com.example.kdy.security.service.UserPrincipal;
import com.example.kdy.security.service.CustomUserDetailsService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Properties;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BoardApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void api_boardWrite() throws Exception {
        UserPrincipal userPrincipal = (UserPrincipal) customUserDetailsService.loadUserByUsername("1");

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userPrincipal, null, userPrincipal.getAuthorities()
                )
        );

        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setBoardTitle("통합 테스트 게시글");
        boardDTO.setBoardContent("첨부파일 없이 JSON만 테스트");

        BoardWriteDTO boardWriteDTO = new BoardWriteDTO();
        boardWriteDTO.setBoardDTO(boardDTO);

        mockMvc.perform(
                        multipart("/api/board/write")
                                .param("boardWriteDTO", objectMapper.writeValueAsString(boardWriteDTO))
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("게시글 작성이 완료되었습니다."));
    }
}
