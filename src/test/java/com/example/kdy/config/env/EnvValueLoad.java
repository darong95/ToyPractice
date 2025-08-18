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
            System.out.println("[ERROR] EnvValueLoad :: " + e.getMessage() + "\n");
        }

        return properties;
    }

    public static String envProperty_GitHub(Properties properties, String secretKey) {
        System.out.println("[CHECK] SECRET KEY :: " + secretKey);

        String envProperty = properties.getProperty(secretKey);
        String secretValue = System.getenv(secretKey); // GitHub SecretKey 값 가져오기
        System.out.println("[CHECK] ENV VALUE :: " + envProperty);
        System.out.println("[CHECK] SECRET VALUE :: " + secretValue);

        return envProperty == null ? secretValue : envProperty;
    }
}
