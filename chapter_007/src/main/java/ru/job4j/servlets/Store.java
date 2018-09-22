package ru.job4j.servlets;

import java.util.Collection;

public interface Store {
    void add(User user);
    void update(User newUser);
    void delete(int id);
    Collection<User> findAll();
    User findByid(int id);
    boolean storeContainsKey(int key);
    boolean storeIsEmpty();
}
