package ru.job4j.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL {
    private final Config config;
    private Connection conn = null;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void generate(int numOfFields) {
        this.conn = this.config.getConn();
        if (this.conn != null) {
            try (Statement statement = this.conn.createStatement()) {
                statement.executeUpdate("CREATE TABLE if NOT EXISTS entry (FIELD INT)");
                if (statement.executeQuery("SELECT * from entry") != null) {
                    statement.executeUpdate("DELETE FROM entry");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String query1 = "INSERT INTO entry(field) VALUES(?)";
            try (PreparedStatement ps = conn.prepareStatement(query1)) {
                for (int i = 1; i <= numOfFields; i++) {
                    ps.setInt(1, i);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<XmlEntries.Field> readDate() {
        List<XmlEntries.Field> result = new ArrayList<>();
        try (Statement stmt = this.conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM entry");
            while (rs.next()) {
                XmlEntries.Field newField = new XmlEntries.Field(rs.getInt(1));
                result.add(newField);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
