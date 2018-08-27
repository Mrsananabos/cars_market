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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (price != item.price) return false;
        if (volume != item.volume) return false;
        if (book != null ? !book.equals(item.book) : item.book != null) return false;
        if (selectedType != item.selectedType) return false;
        return selectedActiion == item.selectedActiion;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (selectedType != null ? selectedType.hashCode() : 0);
        result = 31 * result + (selectedActiion != null ? selectedActiion.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + volume;
        return result;
    }


}

