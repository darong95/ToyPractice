package com.example.kdy.common.component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.*;

@Slf4j
@Component
public class FileUploadComponent {
    @Value("${file.upload-path}")
    private String fileUploadPath; // 파일 업로드 경로

    public Map<String, String> uploadFile(List<MultipartFile> uploadFileList) {
        Map<String, String> fileNameMap = new LinkedHashMap<>();

        try {
            // 파일 선택 여부 확인
            log.info("[FILE] FILE UPLOAD IS START!");

            // 첨부 파일 수만큼 파일 업로드
            for (MultipartFile multipartFile : uploadFileList) {
                if (multipartFile == null || multipartFile.isEmpty()) {
                    continue; // 다음 순서로 이동
                }

                // 원본 파일명, 저장할 파일명 설정
                String[] fileNameArr = multipartFile.getOriginalFilename().split("\\.");

                String fileExtension = fileNameArr[1];
                String fileOriginName = fileNameArr[0];
                String fileUploadName = fileOriginName + " _ " + UUID.randomUUID() + "." + fileExtension;


                // 디렉토리 확인
                File file = new File(fileUploadPath, fileUploadName);
                File fileCheckDir = file.getParentFile();

                // 디렉토리 없으면 생성, 실패 시 예외
                if (!fileCheckDir.exists() && !fileCheckDir.mkdirs()) {
                    throw new IOException("파일 디렉토리 생성에 실패하였습니다.");
                }

                // 파일 서버에 업로드
                multipartFile.transferTo(file);

                log.info("[FILE] FILE ORIGINAL NAME : " + fileOriginName + "." + fileExtension);
                log.info("[FILE] FILE UPLOAD NAME : " + fileUploadName);

                fileNameMap.put(fileOriginName + "." + fileExtension, fileUploadName);
            }

        } catch (IOException e) {
            log.info("파일 업로드에 실패하였습니다.");
        }

        return fileNameMap;
    }
}
