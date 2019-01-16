package dao;

import model.User;
import org.apache.log4j.Logger;
import service.Validate;

import java.sql.*;

public class DBStore implements DBStoreInterface {
    private final Logger LOGGER = Logger.getLogger(DBStore.class);
    private static final Settings SETTINGS = Settings.getInstance();
    private static final String URL = SETTINGS.value("url");
    private static final String USERNAME = SETTINGS.value("user");
    private static final String PASSWORD = SETTINGS.value("password");
    private static final String CREATE_TABLE = SETTINGS.value("createTable");
    private static final String ADD_USER = SETTINGS.value("addUser");
    private static final String FIND_USER_BY_FN = SETTINGS.value("findUserByFN");
    private static final String UPDATE_LAST_NAME = SETTINGS.value("updateLastName");
    private static final String IF_EXIST = SETTINGS.value("ifExist");
    private static final DBStore INSTANCE = new DBStore();

    public DBStore() {
        createTable();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    private void createTable() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean addUser(User user) {
        boolean result = false;
        if (!ifExist(user.getFirstName())) {
            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                  PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            result = true;
        }
        return result;
    }

    @Override
    public User findUserByName(String firstName) {
        User result = null;
        String lastName = null;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_FN)) {
            preparedStatement.setString(1, firstName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    lastName = resultSet.getString("last_name");
                }
            }
            if (lastName != null) {
                result = new User(firstName, lastName);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public User updateLastName(User user) {
        User result = null;
        if (ifExist(user.getFirstName())) {
            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LAST_NAME)) {
                preparedStatement.setString(1, user.getLastName());
                preparedStatement.setString(2, user.getFirstName());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result = user;
        }
        return result;
    }

    @Override
    public boolean ifExist(String firstName) {
        boolean result = false;
        int countRecords = 0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(IF_EXIST)) {
            preparedStatement.setString(1, firstName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    countRecords = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        result = countRecords != 0;
        return result;
    }

}
