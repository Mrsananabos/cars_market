package ru.job4j.carMarket.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private final Properties properties = new Properties();
    private final String NAME_PROPERTIES = "settings.properties";

    public Settings(String path) {
        try {
            properties.load(new FileInputStream(path + File.separator + NAME_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getValue(String key) {
        return this.properties.getProperty(key);
    }

}
