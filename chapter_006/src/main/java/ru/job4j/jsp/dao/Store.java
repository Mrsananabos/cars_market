package ru.job4j.jsp.dao;

import ru.job4j.jsp.User;

import java.util.Collection;

public interface Store {
    void createUsersTable();
    void add(String login, String password, String role, String email,  String address);
    void update(int id, String login, String password, String role, String email, String address);
    void delete(int id);
    Collection<User> findAll();
    User findByid(int id);
    User findByLogin(String login);
    boolean storeContainsKey(int key);
}
