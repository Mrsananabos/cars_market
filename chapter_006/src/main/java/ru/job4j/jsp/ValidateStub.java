package ru.job4j.jsp;

import org.apache.log4j.Logger;
import ru.job4j.jsp.service.Validate;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class ValidateStub implements Validate {
    private final ConcurrentHashMap<Integer, User> store = new ConcurrentHashMap<>();
    private final Logger logger = Logger.getLogger(ValidateStub.class);
    private int ids = 0;

    @Override
    public void add(String login, String password, String role, String email, String address) {
        if (login.isEmpty() || role.isEmpty() || email.isEmpty() || password.isEmpty()) {
            logger.error("Fill in all the fields for add!");
        } else {
            User user = new  User(login, password, Role.valueOf(role), email, address);
            int id = ids++;
            user.setId(id);
            store.put(id, user);
        }
    }

    @Override
    public void update(int id, String login, String password, String role, String email, String address) {
        if (login.isEmpty() || role.isEmpty() || email.isEmpty() || password.isEmpty()) {
            logger.error("Fill in all the fields for edit!");
        } else {
            if (store.containsKey(id)) {
                store.put(id, new  User(login, password, Role.valueOf(role), email, address));
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

}
