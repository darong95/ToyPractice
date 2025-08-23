package com.example.kdy.common.service;

import java.util.HashMap;
import java.util.Map;
import java.time.format.DateTimeFormatter;

public class DateUtilService {
    private final Map<String, DateTimeFormatter> formatTypeMap = new HashMap<>();

    public DateUtilService() {
        // 자주 쓰는 포맷 등록
        formatTypeMap.put("yyyyMMdd", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatTypeMap.put("yyyyMMddmmss", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        formatTypeMap.put("yyyyMMdd_SLASH", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
