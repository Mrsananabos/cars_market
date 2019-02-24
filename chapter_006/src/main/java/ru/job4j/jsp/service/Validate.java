package ru.job4j.jsp.service;

import ru.job4j.jsp.User;

import java.util.Collection;

public interface Validate {

    void add(String login, String password, String role, String email, String address);
    void update(int id, String login, String password, String role, String email, String address);
    void delete(int id);
    Collection<User> findAll();
    User findById(int id);
    User findByLogin(String login);
}
