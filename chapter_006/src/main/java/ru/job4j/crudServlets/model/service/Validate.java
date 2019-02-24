package ru.job4j.crudServlets.model.service;

import ru.job4j.crudServlets.model.GeoPoint;
import ru.job4j.crudServlets.model.User;

import java.util.Collection;

public interface Validate {

    void add(String login, String password, String role, String email, String country, String city);
    void update(int id, String login, String password, String role, String email, String country, String city);
    void delete(int id);
    Collection<User> findAll();
    User findById(int id);
    User findByLogin(String login);
    int isCredential(String login, String password);
    String findRoleByLogin(String login);
    Collection<GeoPoint> getCountries();
    Collection<GeoPoint> getCitiesByCountry(String country);
}
