package ru.job4j.servlets;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Date;
import java.util.List;

public class DBStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final Logger logger = Logger.getLogger(DBStore.class);
    private static final DBStore INSTANCE = new DBStore();
    private static final int[] staticID = {0};

    public DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/postgres");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("a");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        this.createUsersTable();
        logger.info("Подключаем");
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }


    @Override
    public void add(String login, String role, String email, String password, String address) {
        String queryAdd = "INSERT INTO users (id, login, role, email, password, address, date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryAdd);
             )
        {
            ps.setInt(1, staticID[0]++);
            ps.setString(2, login);
            ps.setString(3, role);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, address);
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public void update(int id, String login, String role, String email, String password, String address) {
        String queryUpdate = "UPDATE users SET login = ?, role = ?, email = ?, password = ?, address = ? where id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryUpdate);
        )
        {
            ps.setString(1, login);
            ps.setString(2, role);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, address);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String queryDelete = "DELETE FROM users WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryDelete);
        )
        {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public Collection<User> findAll() {
        Collection<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
        )
        {
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User newUser = new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getDate("date"));
                result.add(newUser);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }


        return result;
    }

    @Override
    public User findByid(int id) {
        User rslUser = null;
        String queryFindById = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryFindById);
        )
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rslUser = new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getDate("date"));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return rslUser;
    }

    @Override
    public boolean storeContainsKey(int id) {
        boolean rsl = true;
        String login = null;
        String queryFindById = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryFindById);
        )
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                       login = rs.getString("login");
            }

            if (login.isEmpty()) {
                rsl = false;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return rsl;
    }



    @Override
    public void createUsersTable() {
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS users " +
                    "(id INT UNIQUE, " +
                    " login VARCHAR(20), " +
                    " role VARCHAR(20), " +
                    " email VARCHAR(30), " +
                    " password VARCHAR(20), " +
                    " address VARCHAR(30), " +
                    " date TIMESTAMP )";
            st.execute(query);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }


}
