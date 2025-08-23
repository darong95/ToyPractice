package com.example.kdy.common.component;

import com.example.kdy.common.component.IF.AccessorEmailInterface;
import com.example.kdy.common.component.IF.AccessorNameInterface;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AsteriskUtilComponent {
    public String asterisk_Name_One(String originName) {
        String tempName = "";
        return tempName;
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
