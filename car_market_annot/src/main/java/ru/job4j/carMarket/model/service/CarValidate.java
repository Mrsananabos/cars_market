package ru.job4j.carMarket.model.service;

import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.FormCarSale;

import java.util.List;

public interface CarValidate {

    List<Car> findCarsByKey(String key);

    Car addCarToUser(FormCarSale formCar);

    void soldCarById(String id);

}
