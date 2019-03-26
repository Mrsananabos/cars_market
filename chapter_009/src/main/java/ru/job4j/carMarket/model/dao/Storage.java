package ru.job4j.carMarket.model.dao;

import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.Mark;
import ru.job4j.toDoList.model.entity.Item;

import java.util.List;

public interface Storage {

    List getMarks();

    List findModelsByMark(int id);

    List getTransmission();

    List getBodyType();

    Car addCar(Car car);

}
