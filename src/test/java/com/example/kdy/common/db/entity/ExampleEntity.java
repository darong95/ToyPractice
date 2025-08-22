package com.example.kdy.common.db.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TP_EXAMPLE")
public class ExampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNum;

    private String userName;
    private String userAge;

    // Getter
    public long getUserNum() {
        return userNum;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAge() {
        return userAge;
    }

    // Setter
    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}
