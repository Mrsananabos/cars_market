package ru.job4j.servlets.model;

import java.util.Collection;

public interface Store {
    void createUsersTable();
    void add(String login, String role, String email, String password, String address);
    void update(int id, String login, String role, String email, String password, String address);
    void delete(int id);
    Collection<User> findAll();
    User findByid(int id);
    User findByLogin(String login);
    boolean storeContainsKey(int key);
    boolean isCredential(String login, String password);
    String findRoleByLogin(String login);
    void createAdmin();

}
