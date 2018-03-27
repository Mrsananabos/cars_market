package ru.job4j.Collections.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DynamicContainerTest {


    @Test
    public void whenCreateFourIntegersThenContainerWillEnlarge() {
        int size = 4;
        DynamicContainer<Integer> container = new DynamicContainer<>(size);
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        container.add(5);
        Integer rsl = container.getSize();
        Integer expect = 8;
        assertThat(rsl, is(expect));
    }


    @Test (expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        DynamicContainer<Integer> container = new DynamicContainer<>(3);
        container.add(1);
        container.add(2);
        container.add(3);
        Iterator it = container.iterator();
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(1));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(2));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(3));
        assertThat(it.hasNext(), Matchers.is(false));
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void shouldThrowConcurrentModificationException() {
        DynamicContainer<Integer> container = new DynamicContainer<>(3);
        container.add(1);
        container.add(2);
        container.add(3);
        Iterator it = container.iterator();
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(1));
        container.add(4);
        it.hasNext();
    }



}