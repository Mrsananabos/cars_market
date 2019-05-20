package ru.job4j.carMarket.model.dao;

import java.util.List;

public interface CommonCarPartDao<T> {

    List<T> getAll();

}
