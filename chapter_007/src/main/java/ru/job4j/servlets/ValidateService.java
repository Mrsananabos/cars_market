package ru.job4j.servlets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import ru.job4j.servlets.MemoryStore;

import javax.jws.soap.SOAPBinding;

public class ValidateService {

    public static final ValidateService INSTANCE = new ValidateService();
    private final Store logic = MemoryStore.getInstance();
    private static final Logger logger = Logger.getLogger(ValidateService.class);

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public void add(User user) {
        if (user != null) {
            logic.add(user);
        } else {
            logger.error("User is empty!");
        }
    }

    public void update(User newUser) {
        if (newUser != null) {
            if (logic.storeContainsKey(newUser.getId())){
                logic.update(newUser);
            } else {
                logger.error("User with such ID doesn't exist!");
            }
        } else {
            logger.error("User for update is empty!");
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
        if (logic.storeIsEmpty()) {
            logger.error("Store is empty!");
        } else {
            rsl = logic.findAll();
        }
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
        return  rsl;
    }


}

