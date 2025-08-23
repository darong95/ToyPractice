package com.example.kdy.common.component.IF;

public interface AccessorNameInterface<T> {
    String getAccessName(T objType);
    void setAccessName(T objType, String settingName);;
}
