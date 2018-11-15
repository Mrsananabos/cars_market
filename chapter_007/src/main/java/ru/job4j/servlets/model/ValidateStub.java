package ru.job4j.servlets.model;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

import java.util.Collection;

public class ValidateStub implements Validate {
    private final ConcurrentHashMap<Integer, User> store = new ConcurrentHashMap<>();
    private final Logger logger = Logger.getLogger(ValidateStub.class);
    private int ids = 0;

    @Override
    public void add(String login, String password, String role, String email, String country, String region, String city) {
        if (login.isEmpty() || role.isEmpty() || email.isEmpty() || password.isEmpty()) {
            logger.error("Fill in all the fields for add!");
        } else {
            User user = new User(login, password, Role.valueOf(role), email, country, region, city);
            int id = ids++;
            user.setId(id);
            store.put(id, user);
        }
    }

    @Override
    public void update(int id, String login, String password, String role, String email, String country, String region, String city) {
        if (login.isEmpty() || role.isEmpty() || email.isEmpty() || password.isEmpty()) {
            logger.error("Fill in all the fields for edit!");
        } else {
            if (store.containsKey(id)) {
                store.put(id, new User(login, password, Role.valueOf(role), email, country, region, city));
            } else {
                logger.error("User with such ID doesn't exist!");
            }

        }
    }

    @Override
    public void delete(int id) {
        store.remove(id);
    }

    @Override
    public Collection<User> findAll() {
        Collection<User> result = null;
        result = store.values();
        return result;
    }

    @Override
    public User findById(int id) {
        return store.get(id);
    }

    @Override
    public User findByLogin(String login) {
        User result = null;
        for (User user : store.values()) {
            if (user.getLogin().equals(login)) {
                result = user;
                break;
            }
        }
        return result;
    }

    @Override
    public int isCredential(String login, String password) {
        int result = -1;
        if (!login.isEmpty() && !password.isEmpty()) {
            for (User user : store.values()) {
                if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    break;
                }
            }
        } else {
            logger.error("Empty fields are not allowed");
        }
        return result;
    }

    @Override
    public String findRoleByLogin(String login) {
        String result = null;
        if (!login.isEmpty()) {
            for (User user : store.values()) {
                if (user.getLogin().equals(login)) {
                    result = user.getRole().name();
                    break;
                }
            }
        } else {
            logger.error("Login is empty");
        }

        return result;
    }

    @Override
    public Collection<GeoPoint> getCountries() {
        return null;
    }

    @Override
    public Collection<GeoPoint> getRegionsByCountry(String country) {
        return null;
    }

    @Override
    public Collection<GeoPoint> getCitiesByRegion(String region) {
        return null;
    }
}
