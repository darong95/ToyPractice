package com.example.kdy.config.env;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;

public class EnvValueLoad {
    public static Properties envPropertiesLoad (String checkProperty) {
        Properties properties = new Properties();

        String _checkProperty = checkProperty == null ? "" : checkProperty;
        System.out.println("[LOG] Check property value :: " + checkProperty);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(_checkProperty));

            bufferedReader
                    .lines()
                    .filter(envLine -> envLine.contains("=") && !envLine.trim().startsWith("#"))

                    .forEach(property_Value -> {
                        String[] property_Split = property_Value.split("=", 2);

                        String envKey = property_Split[0].trim();
                        String envValue = property_Split[1].trim();

                        System.setProperty(envKey, envValue);
                        properties.setProperty(envKey, envValue);
                    });

        } catch (IOException e) {
            System.out.println("[ERROR] 오류가 발생하여 실행을 종료합니다.");
            System.out.println("[ERROR] EnvValueLoad :: " + e.getMessage());
        }

        return properties;
    }
}
