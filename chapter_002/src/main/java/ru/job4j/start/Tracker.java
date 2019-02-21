package ru.job4j.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tracker {
    private static final Logger log = LoggerFactory.getLogger(Tracker.class);
    private Connection conn = null;
    private ControlPSQL controlPSQL = null;
    private static final Random RN = new Random();


    public Tracker(String URL, String USERNAME, String PASSWORD) {
        this.controlPSQL = new ControlPSQL(URL, USERNAME, PASSWORD);
        this.conn = controlPSQL.makeConnection();
        this.createBasicTable();
    }

    public ControlPSQL getControlPSQL() {
        return controlPSQL;
    }

    public void createBasicTable() {
        try (Statement st = this.conn.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS Items " +
                    "(id VARCHAR(20) UNIQUE, " +
                    " name VARCHAR(100), " +
                    " descrip VARCHAR(250), " +
                    " created DATE, " +
                    " comment VARCHAR(250))";
            st.execute(query);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }


    private String generateId() {
        return ((String.valueOf(System.currentTimeMillis() + RN.nextInt())));
    }

    protected Item add(Item item) {
        String stmt = "INSERT INTO items (id, name, descrip, created, comment) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(stmt)) {
            String id = this.generateId();
            item.setId(id);
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDesc());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setString(5, item.getComment());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        System.out.println("New item is created, id = " + item.getId());
        return item;
    }


    protected void update(Item newItem) {
        String query = "UPDATE Items SET name = ?, descrip = ?, created = ?, comment = ? where id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newItem.getName());
            ps.setString(2, newItem.getDesc());
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setString(4, newItem.getComment());
            ps.setString(5, newItem.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    protected void delete(String id) {
        String query = "DELETE FROM Items WHERE id = ?";
        try (PreparedStatement prst = conn.prepareStatement(query)) {
            prst.setString(1, id);
            prst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }


    protected Item findById(String id) {
        Item result = null;
        String query = "SELECT * FROM items WHERE id = ?";
        try (PreparedStatement prst = conn.prepareStatement(query)) {
            prst.setString(1, id);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                result = new Item(rs.getString("name"), rs.getString("comment"), rs.getString("descrip"));
                result.setId(rs.getString("id"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    protected List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        String query = "SELECT * FROM items WHERE name = ?";
        try (PreparedStatement prst = conn.prepareStatement(query)) {
            prst.setString(1, key);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                Item newItem = new Item(rs.getString("name"), rs.getString("comment"), rs.getString("descrip"));
                newItem.setId(rs.getString("id"));
                result.add(newItem);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    public List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM items");
            while (rs.next()) {
                Item newItem = new Item(rs.getString("name"), rs.getString("comment"), rs.getString("descrip"));
                newItem.setId(rs.getString("id"));
                result.add(newItem);
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return
                result;
    }
}