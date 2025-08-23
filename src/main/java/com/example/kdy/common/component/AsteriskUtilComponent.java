package com.example.kdy.common.component;

import com.example.kdy.common.component.IF.AccessorEmailInterface;
import com.example.kdy.common.component.IF.AccessorNameInterface;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AsteriskUtilComponent {
    public String asterisk_Name_One(String originName) {
        // 값이 없을 경우
        if (originName == null || originName.isEmpty()) {
            return "";
        }

        // 이름 길이 가져오기
        int originName_Length = originName.length();
        
        // 한 글자일 경우
        if (originName_Length == 1) {
            return "정상적인 이름이 아닙니다.";

        // 외자일 경우
        } else if (originName_Length == 2) {
            return originName.charAt(0) + "*"; // 성 + 외자 이름
        
        // 3글자 이상일 경우
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(originName.charAt(0)); // 성

            for (int i = 1; i < originName_Length - 1; i++) {
                stringBuilder.append("*"); // 이름 사이즈의 -1만큼 Asterisk
            }

            stringBuilder.append(originName.charAt(originName_Length - 1)); // 마지막 글자

            return stringBuilder.toString();
        }
    }

    public String asterisk_Email_One(String originEmail) {
        String tempEmail = "";
        return tempEmail;
    }

    public <T> void asterisk_Name_List(List<T> originList, AccessorNameInterface<T> accessorNameInterface) {
        if (originList == null || originList.isEmpty()) return;

        for (T originName : originList) {
            String tempName = accessorNameInterface.getAccessName(originName);
            String asteriskName = asterisk_Name_One(tempName); // 이름 Asterisk

            accessorNameInterface.setAccessName(originName, asteriskName); // 변경된 정보로 다시 Setting
        }
    }

    public <T> void asterisk_Email_List(List<T> originList, AccessorEmailInterface<T> accessorEmailInterface) {
        if (originList == null || originList.isEmpty()) return;

        for (T originEmail : originList) {
            String tempEmail = accessorEmailInterface.getAccessEmail(originEmail);
            String asteriskName = asterisk_Email_One(tempEmail); // 이메일 Asterisk

            accessorEmailInterface.setAccessEmail(originEmail, asteriskName); // 변경된 정보로 다시 Setting
        }
    }
}
