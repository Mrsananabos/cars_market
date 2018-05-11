package ru.job4j.collections.control;

import java.util.Random;

public class Item {
    public int id;
    protected String book;
    protected String type;
    protected String action;
    protected int price;
    protected int volume;


    public Item(String book, String type, String action, int price, int volume) {
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
    }

    public Item(int id, String book, String type) {
        this.id = id;
        this.book = book;
        this.type = type;
    }

    protected void setId(int id) {

        this.id = id;
    }

}

