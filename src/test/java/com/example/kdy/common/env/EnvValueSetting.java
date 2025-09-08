package com.example.kdy.common.env;

public class EnvValueSetting { // Env 값 가져오기
    // H2
    private static String dataSourceURL_H2;
    private static String dataSourceUserName_H2;
    private static String dataSourcePassword_H2;

    // MariaDB
    private static String dataSourceURL_MariaDB;
    private static String dataSourceUserName_MariaDB;
    private static String dataSourcePassword_MariaDB;

    private static void checkEnvValue() { // 프로퍼티 값을 가져오는 JUnit 테스트
        EnvValueLoad.envPropertiesLoad("src/main/resources/local.env");

        // JUnit Test를 위한 Property Setting
        dataSourceURL_H2 = System.getProperty("SPRING_DATASOURCE_H2_URL");
        dataSourceURL_MariaDB = System.getProperty("SPRING_DATASOURCE_MARIADB_URL");

        dataSourceUserName_H2 = System.getProperty("SPRING_DATASOURCE_H2_USERNAME");
        dataSourceUserName_MariaDB = System.getProperty("SPRING_DATASOURCE_MARIADB_USERNAME");

        dataSourcePassword_H2 = System.getProperty("SPRING_DATASOURCE_H2_PASSWORD");
        dataSourcePassword_MariaDB = System.getProperty("SPRING_DATASOURCE_MARIADB_PASSWORD");
    }

    public static void loadEnvProperty_H2() {
        checkEnvValue();

        System.out.println("[H2 ENV SETTING]");
        System.out.println("1. URL :: " + dataSourceURL_H2);
        System.out.println("2. USERNAME :: " + dataSourceUserName_H2);
        System.out.println("3. PASSWORD :: " + dataSourcePassword_H2 + "\n");
    }

    public static void loadEnvProperty_MariaDB() {
        checkEnvValue();

        System.out.println("[MARIADB ENV SETTING]");
        System.out.println("1. URL :: " + dataSourceURL_MariaDB);
        System.out.println("2. USERNAME :: " + dataSourceUserName_MariaDB);
        System.out.println("3. PASSWORD :: " + dataSourcePassword_MariaDB + "\n");
    }

    // Getter
    public static String getDataSourceURL_H2() {
        return dataSourceURL_H2;
    }

    public static String getDataSourcePassword_H2() {
        return dataSourcePassword_H2;
    }

    public static String getDataSourceUserName_H2() {
        return dataSourceUserName_H2;
    }

    public static String getDataSourceURL_MariaDB() {
        return dataSourceURL_MariaDB;
    }

    public static String getDataSourceUserName_MariaDB() {
        return dataSourceUserName_MariaDB;
    }

    public static String getDataSourcePassword_MariaDB() {
        return dataSourcePassword_MariaDB;
    }
}
