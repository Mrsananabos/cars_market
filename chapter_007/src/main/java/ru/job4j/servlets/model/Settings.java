package ru.job4j.servlets.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static final Settings INSTANCE = new Settings();
    private final Properties properties = new Properties();

    private Settings() {
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("WEB-INF/resources/settings.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings getInstance() {
        return INSTANCE;
    }

    public String getValue(String key) {
        return this.properties.getProperty(key);
    }

}
