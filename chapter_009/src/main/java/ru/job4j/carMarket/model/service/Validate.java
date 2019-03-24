package ru.job4j.carMarket.model.service;

import ru.job4j.toDoList.model.entity.Item;

import java.util.List;

public interface Validate {

    List findAll();

    Item add(String desc);

    void doneItem(int id);
}
