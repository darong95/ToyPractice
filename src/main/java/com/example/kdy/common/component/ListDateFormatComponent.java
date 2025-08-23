package com.example.kdy.common.component;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class ListDateFormatComponent {
    public <T> List<T> forListDate(List<T> formatList, BiConsumer<T, DateUtilComponent> formatDate, DateUtilComponent dateUtilComponent) {
        // List가 비었을 경우
        if (formatList == null || formatList.isEmpty()) {
            return formatList;
        }

        // 반복문을 돌며 리스트의 날짜 Format
        for (T tempDate : formatList) {
            formatDate.accept(tempDate, dateUtilComponent); // 호출하여 구현하는 로직에서 포맷하는 코드 진행
        }

        return formatList;
    }
}
