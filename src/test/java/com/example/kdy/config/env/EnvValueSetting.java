package com.example.kdy.config.env;

public class EnvValueSetting {
    // H2
    private static String dataSourceURL_H2;
    private static String dataSourceUserName_H2;
    private static String dataSourcePassword_H2;

    // MariaDB
    private static String dataSourceURL_MariaDB;
    private static String dataSourceUserName_MariaDB;
    private static String dataSourcePassword_MariaDB;

    private static void checkEnvValue() {
        EnvValueLoad.envPropertiesLoad("src/main/resources/local.env");

        // JUnit Test를 위한 Property Setting
        dataSourceURL_H2 = System.getProperty("DATASOURCE_URL_H2");
        dataSourceURL_MariaDB = System.getProperty("DATASOURCE_URL_MARIADB");

        dataSourceUserName_H2 = System.getProperty("DATASOURCE_USERNAME_H2");
        dataSourceUserName_MariaDB = System.getProperty("DATASOURCE_USERNAME_MARIADB");

        dataSourcePassword_H2 = System.getProperty("DATASOURCE_PASSWORD_H2");
        dataSourcePassword_MariaDB = System.getProperty("DATASOURCE_PASSWORD_MARIADB");
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
