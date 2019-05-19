package ru.job4j.carMarket.model.service.impl;

import org.apache.log4j.Logger;
import ru.job4j.carMarket.model.dao.impl.UserDaoImpl;
import ru.job4j.carMarket.model.entity.User;
import ru.job4j.carMarket.model.service.UserValidate;

public class UserValidateImpl implements UserValidate {
    private static final UserValidate INSTANCE = new UserValidateImpl();
    private static final Logger LOGGER = Logger.getLogger(UserValidateImpl.class);

    public static UserValidate getInstance() {
        return INSTANCE;
    }

    @Override
    public User addUser(String login, String password) {
        User result = null;
        if (isValid(login) && isValid(password)) {
            User user = UserDaoImpl.getInstance().findUserByLogin(login);
            if (user == null) {
                User newUser = new User();
                newUser.setLogin(login);
                newUser.setPassword(password);
                UserDaoImpl.getInstance().addUser(newUser);
                result = newUser;
            } else {
                LOGGER.info("User with login(" + login + ") is already exists");
            }
        } else {
            LOGGER.info("Data of user are not valid");
        }
        return result;
    }

    @Override
    public User findUserByLogin(String login) {
        User result = null;
        if (isValid(login)) {
            result = UserDaoImpl.getInstance().findUserByLogin(login);
            if (result == null) {
                LOGGER.info("User with login(" + login + ") is not found");
            }
        } else {
            LOGGER.info("login is not valid");
        }
        return result;
    }

    private boolean isValid(String value) {
        return ((value != null) && (!value.isEmpty()));
    }
}
