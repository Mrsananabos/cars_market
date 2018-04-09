package ru.job4j.Collections.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkedContainerTest {


    @Test
    public void whenCreateSevenNodesThenReturnThirdHeadTailNodes() {
        LinkedContainer<Integer> container = new LinkedContainer<Integer>();
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        container.add(5);
        container.add(6);
        container.add(7);
        Integer rsl1 = container.getData(2);
        Integer expect1 = 3;
        assertThat(rsl1, is(expect1));
        Integer rsl2 = container.getHead();
        Integer expect2 = 1;
        assertThat(rsl2, is(expect2));
        Integer rsl3 = container.getTail();
        Integer expect3 = 7;
        assertThat(rsl3, is(expect3));
    }

    @Test
    public void whenCreateFiveNodesThenDeleteThirdHeadTail() {
        LinkedContainer<Integer> container = new LinkedContainer<Integer>();
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        container.add(5);
        container.deleteByIndex(2);
        container.deleteHead();
        container.deleteTail();
        Integer rsl1 = container.getHead();
        assertThat(rsl1, is(2));
        Integer rsl2 = container.getTail();
        assertThat(rsl2, is(4));
    }



    @Test (expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        LinkedContainer<Integer> container = new LinkedContainer<Integer>();

        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);

        Iterator it = container.iterator();
        assertThat(it.hasNext(), Matchers.is(true));
        assertEquals(it.next(), 1);
        assertThat(it.hasNext(), Matchers.is(true));
        assertEquals(it.next(), 2);
        assertThat(it.hasNext(), Matchers.is(true));
        assertEquals(it.next(), 3);
        assertThat(it.hasNext(), Matchers.is(true));
        assertEquals(it.next(), 4);
        assertThat(it.hasNext(), Matchers.is(false));
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void shouldThrowConcurrentModificationException() {
        LinkedContainer<Integer> container = new LinkedContainer<Integer>();
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