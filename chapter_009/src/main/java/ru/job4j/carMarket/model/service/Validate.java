package ru.job4j.carMarket.model.service;

import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.User;

import java.util.List;

public interface Validate {

    List findCarsByKey(String key);

    List findPartsCarByKey(String key);

    List findModelsByMark(String idMark);

    Car addCarToUser(Car car);

    User findUserByLogin(String login);

    boolean addUser(String login, String password);
}
