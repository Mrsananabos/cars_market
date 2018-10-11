package ru.job4j.servlets.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import ru.job4j.servlets.MemoryStore;

import javax.jws.soap.SOAPBinding;

public class ValidateService {
    public static final ValidateService INSTANCE = new ValidateService();
    private final Store logic = DBStore.getInstance();
    private static final Logger logger = Logger.getLogger(ValidateService.class);

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public void add(String login, String role, String email, String password, String address) {
        if (login.isEmpty() || role.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty()) {
            logger.error("Fill in all the fields for add!");
        } else {
            logic.add(login, role, email, password, address);
        }
    }

    public void update(int id, String login, String role, String email, String password, String address) {
        if (login.isEmpty() || role.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty()) {
            logger.error("Fill in all the fields for edit!");
        } else {
            if (logic.storeContainsKey(id)) {
                logic.update(id, login, role, email, password, address);
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


}

