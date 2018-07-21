package ru.job4j.start;

import com.sun.deploy.security.MozillaJSSNONEwithRSASignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sql.SQLStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tracker implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(SQLStorage.class);
    private Connection conn = null;
    private static final Random RN = new Random();
    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    public Tracker(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.makeConnectionSQL();
    }

    public void makeConnectionSQL() {
        try {
            this.conn = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE ITEMS " +
                    "(id VARCHAR(20) UNIQUE, " +
                    " name VARCHAR(100), " +
                    " descrip VARCHAR(250), " +
                    " created DATE, " +
                    " comment VARCHAR(250))";

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
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

    private String generateId() {
        return ((String.valueOf(System.currentTimeMillis() + RN.nextInt())));
    }

    protected Item add(Item item) {
        try {
            item.setId(this.generateId());
            this.conn = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO items (id, name, descrip, created, comment) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDesc());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setString(5, item.getComment());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        System.out.println("New item is created, id = " + item.getId());
        return item;
    }


    protected void update(Item newItem) {
        try {
            this.conn = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
            PreparedStatement ps = conn.prepareStatement("UPDATE Items SET name = ?, descrip = ?, created = ?, comment = ? where id = ?");
            ps.setString(1, newItem.getName());
            ps.setString(2, newItem.getDesc());
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setString(4, newItem.getComment());
            ps.setString(5, newItem.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    protected void delete(String id) {
        try {
            this.conn = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Items WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }


    protected Item findById(String id) {
        Item result = null;
        try {
            this.conn = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
            PreparedStatement st = conn.prepareStatement("SELECT * FROM items WHERE id = ?");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = new Item(rs.getString("name"), rs.getString("comment"), rs.getString("descrip"));
                result.setId(rs.getString("id"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    protected List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try {
            this.conn = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
            PreparedStatement st = conn.prepareStatement("SELECT * FROM items WHERE name = ?");
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item newItem = new Item(rs.getString("name"), rs.getString("comment"), rs.getString("descrip"));
                newItem.setId(rs.getString("id"));
                result.add(newItem);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    public List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        try {
            this.conn = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM items");
            while (rs.next()) {
                Item newItem = new Item(rs.getString("name"), rs.getString("comment"), rs.getString("descrip"));
                newItem.setId(rs.getString("id"));
                result.add(newItem);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}





