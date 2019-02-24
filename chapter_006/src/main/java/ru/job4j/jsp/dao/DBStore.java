package ru.job4j.jsp.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import ru.job4j.jsp.Role;
import ru.job4j.jsp.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DBStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final Logger logger = Logger.getLogger(DBStore.class);
    private static final DBStore INSTANCE = new DBStore();

    public DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/postgres");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("a");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        this.createUsersTable(); }

    public static DBStore getInstance() {
        return INSTANCE;
    }


    @Override
    public void add(String login, String password, String role, String email,  String address) {
        String queryAdd = "INSERT INTO users (login, role, email, password, address, date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryAdd);
        ) {
            ps.setString(1, login);
            ps.setString(2, role);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, address);
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public void update(int id, String login, String password, String role, String email,  String address) {
        String queryUpdate = "UPDATE users SET login = ?, role = ?, email = ?, password = ?, address = ? where id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryUpdate)) {
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
        ) {
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
        ) {
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User newUser = new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")),
                        rs.getString("email"),
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
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    rslUser = new User(
                            rs.getInt("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            Role.valueOf(rs.getString("role")),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getDate("date"));
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return rslUser;
    }

    @Override
    public User findByLogin(String login) {
        User rslUser = null;
        String queryFindByLogin = "SELECT * FROM users WHERE login = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryFindByLogin);
        ) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    rslUser = new User(
                            rs.getInt("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            Role.valueOf(rs.getString("role")),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getDate("date"));
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return rslUser;
    }

    @Override
    public boolean storeContainsKey(int id) {
        System.out.println(id);
        boolean rsl = true;
        String login = null;
        String queryFindById = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryFindById);
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    login = rs.getString("login");
                }

                if (login.isEmpty()) {
                    rsl = false;
                }
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
                    "(id SERIAL PRIMARY KEY , " +
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
