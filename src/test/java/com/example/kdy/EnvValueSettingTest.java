package com.example.kdy;

import com.example.kdy.config.env.EnvValueLoad;
import com.example.kdy.config.env.EnvValueSetting;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EnvValueSettingTest {
    @BeforeAll
    static void initEnvProperty() {
        // EnvValueSetting.loadEnvProperty_H2();
        EnvValueSetting.loadEnvProperty_MariaDB();
    }

    @Test
    public void envSettingCheck() {
        // System.out.println("[LOG] MARIADB_USERNAME :: " + EnvValueSetting.getDataSourceUserName_MariaDB());
    }
}


