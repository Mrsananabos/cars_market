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
        boolean rsl2 = hashMap.insert(569, "Katya");
        assertThat(rsl2, is(true));
        boolean rsl3 = hashMap.insert(81, "Masha");
        assertThat(rsl3, is(true));
        boolean rsl4 = hashMap.insert(9, "Olga");
        assertThat(rsl4, is(false));
        boolean rsl5 = hashMap.insert(38945, "Roma");
        assertThat(rsl5, is(true));
        boolean rsl6 = hashMap.insert(1114, "Oleg");
        assertThat(rsl6, is(true));
        boolean rsl7 = hashMap.delete(38945);
        assertThat(rsl7, is(true));

        Iterator iterator = hashMap.iterator();
        assertThat(iterator.hasNext(), Matchers.is(true));
        assertThat(iterator.next(), Matchers.is("Masha"));
        assertThat(iterator.hasNext(), Matchers.is(true));
        assertThat(iterator.next(), Matchers.is("Oleg"));
        assertThat(iterator.hasNext(), Matchers.is(true));
        assertThat(iterator.next(), Matchers.is("Olga"));
        assertThat(iterator.hasNext(), Matchers.is(true));
        assertThat(iterator.next(), Matchers.is("Katya"));
        assertThat(iterator.hasNext(), Matchers.is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowConcurrentModificationException() {
        SimpleHashMap<Integer, String> hashMap = new SimpleHashMap<>();
        hashMap.insert(111, "Person1");
        hashMap.insert(212, "Person2");
        hashMap.insert(313, "Person3");
        hashMap.insert(414, "Person4");
        hashMap.insert(515, "Person5");
        Iterator it = hashMap.iterator();
        hashMap.insert(616, "Person6");
        hashMap.insert(717, "Person7");
        hashMap.insert(818, "Person8");
        it.hasNext();
    }

    @Test (expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        SimpleHashMap<Integer, String> hashMap = new SimpleHashMap<>();
        hashMap.insert(111, "Person1");
        hashMap.insert(212, "Person2");
        hashMap.insert(313, "Person3");
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