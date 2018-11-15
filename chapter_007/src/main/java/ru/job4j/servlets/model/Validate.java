package ru.job4j.servlets.model;

import java.util.Collection;

public interface Validate {
    void add(String login, String password, String role, String email, String country, String region, String city);
    void update(int id, String login, String password, String role, String email, String country, String region, String city);
    void delete(int id);
    Collection<User> findAll();
    User findById(int id);
    User findByLogin(String login);
    int isCredential(String login, String password);
    String findRoleByLogin(String login);
    Collection<GeoPoint> getCountries();
    Collection<GeoPoint> getRegionsByCountry(String country);
    Collection<GeoPoint> getCitiesByRegion(String region);
}
