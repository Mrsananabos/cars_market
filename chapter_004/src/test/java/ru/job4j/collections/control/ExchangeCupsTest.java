package ru.job4j.collections.control;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExchangeCupsTest {

    @Test
    public void showResult() {
        ExchangeCups cup = new ExchangeCups();
        Item it1 = new Item("Rusal", "add", "ask", 14, 7);
        Item it2 = new Item("Rusal", "add", "ask", 12, 5);
        Item it3 = new Item("Rusal", "add", "bid", 45, 9);
        Item it4 = new Item("Rusal", "add", "ask", 20, 2);
        Item it5 = new Item("Rusal", "add", "bid", 11, 3);
        Item it6 = new Item("Rusal", "add", "ask", 46, 8);
        Item it7 = new Item("Rusal", "add", "bid", 45, 9);
        Item it8 = new Item("Rusal", "add", "bid", 78, 6);
        cup.accept(it1);
        cup.accept(it2);
        cup.accept(it3);
        cup.accept(it4);
        cup.accept(it5);
        cup.accept(it6);
        cup.accept(it7);
        cup.accept(it8);
        Item it9 = new Item(it8.id, "Rusal", "delete");
        cup.accept(it9);
        cup.printByBook("Rusal");
    }

}