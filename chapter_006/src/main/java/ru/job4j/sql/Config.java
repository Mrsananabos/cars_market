package ru.job4j.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private final String URL;
    private Connection c = null;

    public Config(String url) {
        URL = url;
    }
    protected Connection getConn() {
        try {
            c = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.c;
    }
    protected void closeConnection() {
        try {
            this.c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
