package ru.job4j.carMarket.model.dao;

import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.User;

import java.util.List;

public interface CarDao {

    List<Car> getCars();

    Car addCarToUser(User user, Car car);

    void soldCar(int id);
}

