package ru.job4j.servlets.model;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.dbcp2.BasicDataSource;

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
        this.createUsersTable();
        this.add("admin", "admin", "admin", "admin@bk.ru", "Россия", "Спб и ЛО", "Санкт-Петербург");
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }


    @Override
    public void add(String login, String password, String role, String email, String country, String region, String city) {
        String queryAdd = "INSERT INTO users (login, password, role, email, country, region, city, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryAdd);
        ) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setString(4, email);
            ps.setString(5, country);
            ps.setString(6, region);
            ps.setString(7, city);
            ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public void update(int id, String login, String password, String role, String email, String country, String region, String city) {
        String queryUpdate = "UPDATE users SET login = ?, password = ?, role = ?, email = ?, country = ?, region = ?, city = ? where id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryUpdate);
        ) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setString(4, email);
            ps.setString(5, country);
            ps.setString(6, region);
            ps.setString(7, city);
            ps.setInt(8, id);
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
            ResultSet rs = statement.executeQuery("SELECT * FROM users ORDER BY id");
            while (rs.next()) {
                User newUser = new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")),
                        rs.getString("email"),
                        rs.getString("country"),
                        rs.getString("region"),
                        rs.getString("city"),
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
                            rs.getString("country"),
                            rs.getString("region"),
                            rs.getString("city"),
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
                            rs.getString("country"),
                            rs.getString("region"),
                            rs.getString("city"),
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
                    " password VARCHAR(20), " +
                    " role VARCHAR(20), " +
                    " email VARCHAR(30), " +
                    " country VARCHAR(50), " +
                    " region VARCHAR(50), " +
                    " city VARCHAR(50), " +
                    " date TIMESTAMP )";
            st.execute(query);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public int isCredential(String login, String password) {
        int id = -1;
        String queryFindById = "SELECT id FROM users WHERE login = ? AND password = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryFindById);
        ) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }


        return id;
    }

    @Override
    public String findRoleByLogin(String login) {
        String result = null;
        String queryFindRoleByLogin = "SELECT role FROM users WHERE login = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryFindRoleByLogin);
        ) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result = rs.getString("role");
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Collection<GeoPoint> getCountries() {
        String id;
        String name;
        Collection<GeoPoint> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery("SELECT id, name FROM country");
            while (rs.next()) {
                id = rs.getString("id");
                name = rs.getString("name");
                result.add(new GeoPoint(id, name));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return result;
    }

    @Override
    public Collection<GeoPoint> getRegionsByCountry(String country) {
        Collection<GeoPoint> result = new ArrayList<>();
        String queryFindCountry = "SELECT id, name FROM region WHERE country_id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryFindCountry);
        ) {
            ps.setInt(1, Integer.valueOf(country));
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    GeoPoint point = new GeoPoint(id, name);
                    result.add(point);
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Collection<GeoPoint> getCitiesByRegion(String region) {
        Collection<GeoPoint> result = new ArrayList<>();
        String queryFindRegion = "SELECT id, name FROM city WHERE region_id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryFindRegion);
        ) {
            ps.setInt(1, Integer.valueOf(region));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    GeoPoint point = new GeoPoint(id, name);
                    result.add(point);
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
