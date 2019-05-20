package ru.job4j.carMarket.model.dao;

import ru.job4j.carMarket.model.entity.*;

import java.util.List;

public interface Storage {

    List<Car> getCars();

    List<Mark> getMarks();

    List<Model> findModelsByMark(int id);

    List<Transmission> getTransmission();

    List<BodyType> getBodyType();

    Car addCarToUser(User user, Car car);

    User addUser(User user);

    User findUserByLogin(String login);

    void soldCar(int id);

}
