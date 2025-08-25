package com.example.kdy.board.service;

import com.example.kdy.board.dto.BoardFileDTO;
import com.example.kdy.board.entity.BoardEntity;
import com.example.kdy.board.entity.BoardFileEntity;
import com.example.kdy.board.mapper.BoardFileMapper;

import com.example.kdy.common.component.FileDownloadComponent;
import com.example.kdy.common.component.FileUploadComponent;
import jakarta.persistence.EntityManager;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.kdy.board.repository.BoardFileRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardFileService {
    private final EntityManager entityManager; // 파일이 많을 경우 Bulk Insert

    private final BoardFileMapper boardFileMapper;
    private final BoardFileRepository boardFileRepository;

    private final FileUploadComponent fileUploadComponent; // 파일 업로드
    private final FileDownloadComponent fileDownloadComponent; // 파일 다운로드

    @Value("${file.upload-path}")
    private String fileUploadPath; // 파일 업로드 경로

    public List<BoardFileDTO> boardFileList(Long boardSeq) {
        List<BoardFileEntity> boardFileList= boardFileRepository.findBoardFileASC(boardSeq);
        return boardFileMapper.convertToReadListDTO(boardFileList);
    }

    @Transactional(readOnly = true)
    public void boardFileDownload(Long boardFileSeq, HttpServletResponse httpServletResponse) {
        BoardFileEntity boardFileEntity = boardFileRepository.findById(boardFileSeq)
                        .orElseThrow(() -> new RuntimeException("파일이 존재하지 않습니다."));

        String filePath = boardFileEntity.getBoardFilePath();
        String fileOriginName = boardFileEntity.getBoardFileName();
        String fileUploadName = boardFileEntity.getBoardFileUploadName();

        fileDownloadComponent.fileDownload(httpServletResponse, filePath, fileOriginName, fileUploadName);
    }

    public void boardFileWrite(Long boardSeq, List<MultipartFile> boardFileList) {
        Map<String, String> fileNameMap = fileUploadComponent.uploadFile(boardFileList);

        if (!fileNameMap.isEmpty()) {
            for (int i = 0; i < fileNameMap.size(); i++) {
                BoardFileEntity boardFileEntity = new BoardFileEntity();

                String fileOriginName = boardFileList.get(i).getOriginalFilename();
                String fileUploadName = fileNameMap.get(fileOriginName);

                BoardEntity boardEntity = entityManager.getReference(BoardEntity.class, boardSeq); // B_SEQ Setting
                boardFileEntity.setBoardEntity(boardEntity);

                boardFileEntity.setBoardFileName(fileOriginName);
                boardFileEntity.setBoardFileUploadName(fileUploadName);
                boardFileEntity.setBoardFilePath(fileUploadPath);
                boardFileEntity.setBoardFileFullPath(fileUploadPath + "/" + fileUploadName);

                boardFileRepository.save(boardFileEntity);

                /*
                entityManager.persist(boardFileEntity);

                if (i % 10 == 0 && i > 0) { // 10개 단위로 INSERT
                    entityManager.flush();
                    entityManager.clear();
                }
                */
            }
            /*
            entityManager.flush();
            entityManager.clear();
            */
        }
    }

    @Transactional
    public void boardFileDeleteBatch(Long boardSeq) {
        boardFileRepository.deleteByBoardEntityBoardSeq(boardSeq);
    }
}
