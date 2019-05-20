package ru.job4j.carMarket.model.service;

import ru.job4j.carMarket.model.entity.Model;

import java.util.List;

public interface ModelValidate {

    List<Model> findModelsByMark(String id);

}
