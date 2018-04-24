package ru.job4j.collections.map;

import org.hamcrest.Matchers;
import org.junit.Test;

import javax.swing.text.html.HTMLDocument;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenAddCellWithDifferentAndSameHash() {
        SimpleHashMap<Integer, String> hashMap = new SimpleHashMap<>();
        boolean rsl1 = hashMap.insert(27, "Olga");
        assertThat(rsl1, is(true));
        boolean rsl2 = hashMap.insert(567, "Katya");
        assertThat(rsl2, is(true));
        boolean rsl3 = hashMap.insert(87, "Masha");
        assertThat(rsl3, is(true));
        boolean rsl4 = hashMap.insert(8, "Olga"); //номер ячейки по ключу 87 и 8 будет одинаковый
        assertThat(rsl4, is(false));
        boolean rsl5 = hashMap.insert(38945, "Roma");
        assertThat(rsl5, is(true));
        boolean rsl6 = hashMap.insert(1111, "Oleg");
        assertThat(rsl6, is(true));
        boolean rsl7 = hashMap.delete(38945);
        assertThat(rsl7, is(true));

        Iterator iterator = hashMap.iterator();
        assertThat(iterator.hasNext(), Matchers.is(true));
        assertThat(iterator.next(), Matchers.is("Oleg"));
        assertThat(iterator.hasNext(), Matchers.is(true));
        assertThat(iterator.next(), Matchers.is("Olga"));
        assertThat(iterator.hasNext(), Matchers.is(true));
        assertThat(iterator.next(), Matchers.is("Katya"));
        assertThat(iterator.hasNext(), Matchers.is(true));
        assertThat(iterator.next(), Matchers.is("Masha"));
        assertThat(iterator.hasNext(), Matchers.is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowConcurrentModificationException() {
        SimpleHashMap<Integer, String> hashMap = new SimpleHashMap<>();
        hashMap.insert(111, "Person1");
        hashMap.insert(211, "Person2");
        hashMap.insert(311, "Person3");
        hashMap.insert(411, "Person4");
        hashMap.insert(511, "Person5");
        Iterator it = hashMap.iterator();
        hashMap.insert(611, "Person6");
        hashMap.insert(711, "Person7");
        hashMap.insert(811, "Person8");
        it.hasNext();
    }

    @Test (expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        SimpleHashMap<Integer, String> hashMap = new SimpleHashMap<>();
        hashMap.insert(111, "Person1");
        hashMap.insert(211, "Person2");
        hashMap.insert(311, "Person3");
        Iterator it = hashMap.iterator();
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is("Person1"));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is("Person2"));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is("Person3"));
        assertThat(it.hasNext(), Matchers.is(false));
        it.next();
    }


}