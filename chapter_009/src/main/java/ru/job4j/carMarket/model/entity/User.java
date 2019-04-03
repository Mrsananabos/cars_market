package ru.job4j.carMarket.model.entity;

import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Car> cars;

    public User() {
    }

    public User(String login, String password, List<Car> cars) {
        this.login = login;
        this.password = password;
        this.cars = cars;
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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login=" + login +
                ", password=" + password +
                ", cars=" + cars +
                '}';
    }
}
