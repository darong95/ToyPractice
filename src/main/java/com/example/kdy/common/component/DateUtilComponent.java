package com.example.kdy.common.component;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import java.util.HashMap;
import java.util.Map;

@Component
public class DateUtilComponent {
    private final Map<String, DateTimeFormatter> formatTypeMap = new HashMap<>();

    public DateUtilComponent() { // 자주 쓰는 포맷 등록
        formatTypeMap.put("yyyyMMdd", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatTypeMap.put("yyyyMMddmmss", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        formatTypeMap.put("yyyyMMdd_SLASH", DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        // 데이터 타입이 마이크로일 경우
        formatTypeMap.put("yyyyMMddmmss_MICRO", new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .optionalStart()
                .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true) // 6자리까지 가능
                .optionalEnd()
                .toFormatter());
    }

    public LocalDateTime getCurrentDate_MICRO() {
        return LocalDateTime.now();
    }

    public String format_String(String targetDate, String formatType) { // String
        String tempDateFormat = "";

        DateTimeFormatter basicFormatter = formatTypeMap.get("yyyyMMddmmss_MICRO"); // 기본 Format
        DateTimeFormatter targetFormatter = formatTypeMap.get(formatType); // 변경 하려는 Format

        // 기본 Format 1차 변경
        LocalDateTime localDateTime = LocalDateTime.parse(targetDate, basicFormatter);

        // 요청 Format 2차 변경
        tempDateFormat = localDateTime.format(targetFormatter);

        return tempDateFormat;
    }
}
