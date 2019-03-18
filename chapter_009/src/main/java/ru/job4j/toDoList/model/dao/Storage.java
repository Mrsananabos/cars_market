package ru.job4j.toDoList.model.dao;

import ru.job4j.toDoList.model.entity.Item;

import java.util.List;

public interface Storage {

    Item add(Item item);

    Item update(Item item);

    void delete(Item item);

    List findAll();

    void doneItem(int id);
}
