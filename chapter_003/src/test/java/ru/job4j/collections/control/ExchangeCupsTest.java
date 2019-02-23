package ru.job4j.collections.control;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.job4j.collections.control.Item.Action.ASK;
import static ru.job4j.collections.control.Item.Action.BID;
import static ru.job4j.collections.control.Item.Type.ADD;
import static ru.job4j.collections.control.Item.Type.DELETED;

public class ExchangeCupsTest {

    @Test
    public void showResult() {
        ExchangeCups cup = new ExchangeCups();
        Item it1 = new Item("Rusal", ADD, ASK, 14, 7);
        Item it2 = new Item("Rusal", ADD, ASK, 12, 5);
        Item it3 = new Item("Rusal", ADD, BID, 45, 9);
        Item it4 = new Item("Rusal", ADD, ASK, 20, 2);
        Item it5 = new Item("Rusal", ADD, BID, 11, 3);
        Item it6 = new Item("Rusal", ADD, ASK, 46, 8);
        Item it7 = new Item("Rusal", ADD, BID, 45, 9);
        Item it8 = new Item("Rusal", ADD, BID, 78, 6);
        cup.accept(it1);
        cup.accept(it2);
        cup.accept(it3);
        cup.accept(it4);
        cup.accept(it5);
        cup.accept(it6);
        cup.accept(it7);
        cup.accept(it8);
        Item it9 = new Item(it8.getId(), "Rusal", DELETED);
        cup.accept(it9);
        cup.printByBook("Rusal");
    }

}