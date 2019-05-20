package ru.job4j.carMarket.model.dao;

import ru.job4j.carMarket.model.entity.Model;

import java.util.List;

public interface ModelDao {

    List<Model> findModelsByMark(int id);
}
