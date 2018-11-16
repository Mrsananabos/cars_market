package ru.job4j.servlets.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public interface Store {

    void createUsersTable();
    void add(String login, String role, String email, String password, String country, String region, String city);
    void update(int id, String login, String role, String email, String password, String country, String region, String city);
    void delete(int id);
    Collection<User> findAll();
    User findByid(int id);
    User findByLogin(String login);
    boolean storeContainsKey(int key);
    int isCredential(String login, String password);
    String findRoleByLogin(String login);
    Collection<GeoPoint> getCountries();
    Collection<GeoPoint> getRegionsByCountry(String country);
    Collection<GeoPoint> getCitiesByRegion(String region);
}
