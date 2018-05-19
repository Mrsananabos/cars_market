package ru.job4j.collections.control;

import java.util.Random;

import static ru.job4j.collections.control.Item.Type.ADD;

public class Item {
    private int id;
    protected String book;
    protected enum Type { ADD, DELETED };
    protected Type selectedType;
    protected enum Action { ASK, BID };
    protected Action selectedActiion;
    protected int price;
    protected int volume;


    public Item(String book, Type type, Action action, int price, int volume) {
        this.book = book;
        this.selectedType = type;
        this.selectedActiion = action;
        this.price = price;
        this.volume = volume;
    }

    public Item(int id, String book, Type type) {
        this.id = id;
        this.book = book;
        this.selectedType = type;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected int getId() {
        return this.id;
    }



}

