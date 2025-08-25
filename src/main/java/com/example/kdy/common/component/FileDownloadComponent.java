package com.example.kdy.common.component;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
public class FileDownloadComponent {
    public void fileDownload(HttpServletResponse httpServletResponse, String filePath, String originName, String uploadName) {
        log.info("[DOWNLOAD] FILE PATH : " + filePath);
        log.info("[DOWNLOAD] FILE ORIGIN NAME : " + originName);
        log.info("[DOWNLOAD] FILE UPLOAD NAME : " + uploadName);

        try {
            String fileFullPath = filePath + "/" + uploadName;
            log.info("[DOWNLOAD] FILE FULL PATH : " + fileFullPath);

            // 파일 존재 여부 체크
            File file = new File(fileFullPath);

            if (!file.exists()) {
                throw new RuntimeException("파일이 존재하지 않습니다.");
            }

            // ContentType Auto Setting
            String fileContentType = Files.probeContentType(Path.of(filePath));
            httpServletResponse.setContentType(fileContentType);

            // Encoding Setting
            String encodeName = URLEncoder.encode(originName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"");

            // File Download
            try (InputStream inputStream = new FileInputStream(file);
                 OutputStream outputStream = httpServletResponse.getOutputStream()) {

                int bytesRead;
                byte[] readBuffer = new byte[8192];

                while ((bytesRead = inputStream.read(readBuffer)) != - 1) {
                    outputStream.write(readBuffer, 0, bytesRead);
                }

                outputStream.flush();
            }

        } catch (IOException e) {
            log.info("[ERROR] :: " + e.getMessage());
            throw new RuntimeException("파일 다운로드 도중 오류가 발생햐였습니다. 자세한 내용은 담당자에게 문의하여 주시기 바랍니다.");
        }
    }
}
