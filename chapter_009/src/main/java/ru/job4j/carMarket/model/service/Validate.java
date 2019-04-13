package ru.job4j.carMarket.model.service;

import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.FormCarSale;
import ru.job4j.carMarket.model.entity.Model;
import ru.job4j.carMarket.model.entity.User;

import java.util.List;

public interface Validate {

    List<Car> findCarsByKey(String key);

    List findPartsCarByKey(String key);

    List<Model> findModelsByMark(String idMark);

    Car addCarToUser(FormCarSale formCar);

    User findUserByLogin(String login);

    boolean addUser(String login, String password);
}
