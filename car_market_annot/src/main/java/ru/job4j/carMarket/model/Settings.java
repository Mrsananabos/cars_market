package ru.job4j.carMarket.model;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static final Logger LOGGER = Logger.getLogger(Settings.class);
    private final Properties properties = new Properties();
    private static final String NAME_PROPERTIES = "settings.properties";

    public Settings(String path) {
        try {
            properties.load(new FileInputStream(path + File.separator + NAME_PROPERTIES));
        } catch (IOException e) {
           LOGGER.error(e.getMessage(), e);
        }
    }


    public String getValue(String key) {
        return this.properties.getProperty(key);
    }

}
