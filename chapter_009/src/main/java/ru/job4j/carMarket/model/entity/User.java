package ru.job4j.carMarket.model.entity;

import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Car> cars;

    public User() {};

    public User(int id, String login, String password, List<Car> cars) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.cars = cars;
    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> carList) {
        this.cars = carList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
