package ru.job4j.carMarket.model.dao;

import ru.job4j.carMarket.model.entity.User;

public interface UserDao {

    User addUser(User user);

    User findUserByLogin(String login);

}
