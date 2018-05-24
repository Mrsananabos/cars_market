package ru.job4j.collections.set;


import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleSetTest {

    @Test
    public void whenAddSameValuesOfObjects() {
        SimpleSet<Integer> s = new SimpleSet<>(10);
        s.add(1);
        s.add(2);
        s.add(2);
        s.add(1);
        s.add(3);
        Iterator it = s.iterator();
        Integer rsl1 = (Integer) it.next();
        assertThat(rsl1, is(1));
        Integer rsl2 = (Integer) it.next();
        assertThat(rsl2, is(2));
        Integer rsl3 = (Integer) it.next();
        assertThat(rsl3, is(3));
    }

    @Test
    public void whenAddValuesThenSimpleSetEnlarge() {
        SimpleSet<Integer> s = new SimpleSet<>(4);
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);
        s.add(5);
        int rsl = s.getSize();
        assertThat(rsl, is(8));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void shouldThrowConcurrentModificationException() {
        SimpleSet<Integer> s = new SimpleSet<>(3);
        s.add(1);
        s.add(2);
        s.add(2);
        s.add(1);
        s.add(3);
        Iterator it = s.iterator();
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(1));
        s.add(4);
        it.hasNext();
    }




}

