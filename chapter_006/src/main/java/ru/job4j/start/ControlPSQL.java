package ru.job4j.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sql.SQLStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ControlPSQL {
    private static final Logger log = LoggerFactory.getLogger(SQLStorage.class);
    private Connection conn = null;
    private String URL;
    private String USERNAME;
    private String PASSWORD;

    public ControlPSQL(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    protected Connection makeConnection() {
        try {
            this.conn = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return this.conn;
    }

    protected void closeConnection() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

}
