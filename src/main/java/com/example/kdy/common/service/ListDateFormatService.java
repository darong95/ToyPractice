package com.example.kdy.common.service;

import java.util.List;
import java.util.function.BiConsumer;

public class ListDateFormatService {
    public <T> List<T> forListDate(List<T> formatList, BiConsumer<T, DateUtilService> formatDate, DateUtilService dateUtilService) {
        // List가 비었을 경우
        if (formatList == null || formatList.isEmpty()) {
            return formatList;
        }

        // 반복문을 돌며 리스트의 날짜 Format
        for (T tempDate : formatList) {
            formatDate.accept(tempDate, dateUtilService);
        }

        return formatList;
    }
}
