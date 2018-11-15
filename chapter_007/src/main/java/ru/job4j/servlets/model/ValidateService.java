package ru.job4j.servlets.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import javax.jws.soap.SOAPBinding;

public class ValidateService implements Validate {
    public static final Validate INSTANCE = new ValidateService();
    private final Store logic = DBStore.getInstance();
    private static final Logger logger = Logger.getLogger(ValidateService.class);

    public static Validate getInstance() {
        return INSTANCE;
    }

    public void add(String login, String password, String role, String email, String country, String region, String city) {
        if (login.isEmpty() || role.isEmpty() || email.isEmpty() || password.isEmpty() || country.isEmpty()  || region.isEmpty() || city.isEmpty()) {
            logger.error("Fill in all the fields for add!");
        } else {
            logic.add(login, password, role, email, country, region, city);
        }
    }

    public void update(int id, String login, String password, String role, String email, String country, String region, String city) {
        if (login.isEmpty() || role.isEmpty() || email.isEmpty() || password.isEmpty() || country.isEmpty()  || region.isEmpty() || city.isEmpty()) {
            logger.error("Fill in all the fields for edit!");
        } else {
            if (logic.storeContainsKey(id)) {
                logic.update(id, login, password, role, email, country, region, city);
            } else {
                logger.error("User with such ID doesn't exist!");
            }

        }
    }


    public void delete(int id) {
        if (logic.storeContainsKey(id)) {
            logic.delete(id);
        } else {
            logger.error("User with such ID doesn't exist!");
        }
    }

    public Collection<User> findAll() {
        Collection<User> rsl = new ArrayList<User>();
        rsl = logic.findAll();
        return rsl;
    }

    public User findById(int id) {
        User rsl = null;
        try {
            if (logic.storeContainsKey(id)) {
                rsl = logic.findByid(id);
            } else {
                throw new Exception("exception");
            }
        } catch (Exception e) {
            logger.error("User with such ID doesn't exist!");
        }
        return rsl;
    }

    public User findByLogin(String login) {
        User rsl = null;
        if (!login.isEmpty()) {
            rsl = logic.findByLogin(login);
        } else {
            logger.error("Login is empty");
        }
        return rsl;
    }

    public int isCredential(String login, String password) {
        int result = -1;
        if (!login.isEmpty() && !password.isEmpty()) {
            result = logic.isCredential(login, password);
        } else {
            logger.error("Empty fields are not allowed");
        }
        return result;
    }

    public String findRoleByLogin(String login) {
        String result = null;
        if (!login.isEmpty()) {
            result = logic.findRoleByLogin(login);
        } else {
            logger.error("Login is empty");
        }
        return result;
    }

    @Override
    public Collection<GeoPoint> getCountries() {
        Collection<GeoPoint> rsl = new ArrayList<>();
        rsl = logic.getCountries();
        if (rsl.isEmpty()) {
            logger.info("Countries not found");
        }
        return rsl;
    }

    @Override
    public Collection<GeoPoint> getRegionsByCountry(String country) {
        Collection<GeoPoint> rsl = new ArrayList<>();
        if (country.isEmpty()) {
            logger.info("Country is empty");
        }
        rsl = logic.getRegionsByCountry(country);
        if (rsl.isEmpty()) {
            logger.info("Regions not found");
        }
        return rsl;
    }

    @Override
    public Collection<GeoPoint> getCitiesByRegion(String region) {
        Collection<GeoPoint> rsl = new ArrayList<>();
        if (region.isEmpty()) {
            logger.info("Region is empty");
        }
        rsl = logic.getCitiesByRegion(region);
        if (rsl.isEmpty()) {
            logger.info("Cities not found");
        }
        return rsl;
    }

}

