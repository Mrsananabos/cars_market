package ru.job4j.carMarket.model.dao;

import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.User;

import java.util.List;

public interface Storage {

    List getCars();

    List getMarks();

    List findModelsByMark(int id);

    List getTransmission();

    List getBodyType();

    Car addCar(Car car);

    User addUser(User user);

    User findUserByLogin(String login);

//    Car addCarToUser(User user, Car car);

}
