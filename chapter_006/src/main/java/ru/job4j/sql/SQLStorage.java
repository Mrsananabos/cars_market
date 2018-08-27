package ru.job4j.sql;


import org.slf4j.*;
import sun.rmi.runtime.Log;

import java.sql.*;
import java.util.Properties;


public class SQLStorage {
    private static final Logger log = LoggerFactory.getLogger(SQLStorage.class);
    private static Connection conn;
    static String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
    static String username = "postgres";
    static String password = "a";

    public SQLStorage() {
        makeConnectionSQL();
    }

    public static void makeConnectionSQL() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void closeConnectionSQL() {
        if (this.conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static void main(String[] args) {
        makeConnectionSQL();
     /*   try (Statement statement = conn.createStatement()) {
            String sql = "CREATE TABLE Rf " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            statement.executeQuery(sql); */
        String quary = "INSERT INTO type (name) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(quary)) {
            ps.setString(1, "овощи");
            ps.executeUpdate();
    } catch(
    SQLException e) {

        log.error(e.getMessage(), e);
    }

}
}
