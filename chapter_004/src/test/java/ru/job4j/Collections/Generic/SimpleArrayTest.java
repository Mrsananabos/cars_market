package ru.job4j.Collections.Generic;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {


    @Test
    public void whenCreateFiveIntegersThenOneDelete() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(23);
        simpleArray.add(89);
        simpleArray.add(44);
        simpleArray.add(9);
        simpleArray.add(200);
        simpleArray.delete(2);
        Integer rsl = 9;
        Integer expect = simpleArray.get(2);
        assertThat(rsl, is(expect));
    }


    @Test (expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(10);
        simpleArray.add(20);
        simpleArray.add(20);
        simpleArray.set(2, 30);
        Iterator it = simpleArray.iterator();
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(10));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(20));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(30));
        assertThat(it.hasNext(), Matchers.is(false));
        it.next();
    }



}