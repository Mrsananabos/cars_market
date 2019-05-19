package ru.job4j.carMarket.model.service;

import ru.job4j.carMarket.model.entity.User;

public interface UserValidate {

    User addUser(String login, String password);

    User findUserByLogin(String login);
}
