package ru.job4j.parsing;

import org.apache.log4j.Logger;

import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class JdbkStorage {
    private Connection connection;
    private static Logger logger = Logger.getLogger(JobParser.class);

    public JdbkStorage() {
        final Settings settings = Settings.getInstance();
        try {
            this.connection = DriverManager.getConnection(settings.value("jdbk.url"), settings.value("jdbk.username"), settings.value("jdbk.password"));
            logger.info("connection to the DB is received successfuly");
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        createTable();
    }


    public void createTable() {
        if (this.connection != null) {
            try (Statement statement = this.connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE if NOT EXISTS vacancies"
                        + "(id SERIAL PRIMARY KEY, "
                        + " name TEXT, "
                        + " text TEXT, "
                        + " publication_date TIMESTAMP)");
                logger.info("Vacancies table has been created successfuly");
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public int countLinesOfTable() {
        int result = 0;
        try (Statement statement = this.connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT count(id) FROM vacancies");
            while (rs.next()) {
                result = rs.getInt("count");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    public Timestamp findTimestampById(int id) {
        Timestamp result = null;
        String query = "SELECT publication_date FROM vacancies WHERE id = ?";
        try (PreparedStatement prst = this.connection.prepareStatement(query)) {
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                result = rs.getTimestamp("publication_date");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }


    public void listVacanciesWriteIn(List<Vacancy> vacancies) {
        if (!vacancies.isEmpty()) {
            String stmt = "INSERT INTO vacancies (name, text, publication_date) VALUES (?, ?, ?)";
            try (PreparedStatement ps = this.connection.prepareStatement(stmt)) {
                this.connection.setAutoCommit(false);
                for (Vacancy vacancy : vacancies) {

                    ps.setString(1, vacancy.getName());
                    ps.setString(2, vacancy.getText());
                    ps.setTimestamp(3, vacancy.getDatePublished());
                    ps.addBatch();
                }

                ps.executeBatch();
                this.connection.commit();
                this.connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


}
