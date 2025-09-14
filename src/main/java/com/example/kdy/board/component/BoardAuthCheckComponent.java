package com.example.kdy.board.component;

import com.example.kdy.security.SecurityUtil;
import org.springframework.stereotype.Component;

@Component
public class BoardAuthCheckComponent {
    public boolean compare_One(Long compareSeq) {
        Long loginUserSeq = SecurityUtil.getLoginUserSeq();

        if (loginUserSeq.equals(1L)) {
            return true; // 최고 관리자는 가능함

        } else {
            return compareSeq.equals(loginUserSeq);
        }
    }
}
