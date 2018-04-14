package ru.job4j.Collections.Set;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SetHashTableTest {
    @Test
    public void whenAddCellHashTableThenRemoveOneOfThem() {
        SetHashTable<String> table = new SetHashTable<String>(9);
        table.add("Olga");
        table.add("Anna");
        table.add("Oleg");
        table.add("Roman");
        table.add("Anton");
        table.remove("Oleg");
        boolean rsl1 = table.contains("Oleg");
        assertThat(rsl1, Matchers.is(false));
        boolean rsl2 = table.contains("Roman");
        assertThat(rsl2, Matchers.is(true));
    }


    @Test
    public void whenHashTableAddCellThenEnlarge() {
        SetHashTable<String> table = new SetHashTable<String>(3);
        table.add("Olga");
        table.add("Anna");
        table.add("Oleg");
        table.add("Roman");
        table.add("Anton");
        int rsl = table.getSize();
        assertThat(rsl, Matchers.is(6));
    }

}