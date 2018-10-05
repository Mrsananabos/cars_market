package ru.job4j.servlets;

import java.util.Collection;

public interface Store {

    void add(String login, String role, String email, String password, String address);
    void update(int id, String login, String role, String email, String password, String address);
    void delete(int id);
    Collection<User> findAll();
    User findByid(int id);
    boolean storeContainsKey(int key);
    void createUsersTable();
}
