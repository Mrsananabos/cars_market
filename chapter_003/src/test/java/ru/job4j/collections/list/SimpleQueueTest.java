package ru.job4j.collections.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    @Test
    public void whenCreateFiveNodeThenAllPoll() {
        SimpleQueue<Integer> stack = new SimpleQueue<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        Integer rsl = stack.poll();
        assertThat(rsl, is(10));
        Integer rsl1 = stack.poll();
        assertThat(rsl1, is(20));
        Integer rs2 = stack.poll();
        assertThat(rs2, is(30));
        Integer rs3 = stack.poll();
        assertThat(rs3, is(40));
        Integer rs4 = stack.poll();
        assertThat(rs4, is(50));
    }


    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        SimpleQueue<Integer> stack = new SimpleQueue<>();
        stack.push(30);
        stack.push(40);
        stack.push(50);
        Integer rsl = stack.poll();
        assertThat(rsl, is(30));
        Integer rsl1 = stack.poll();
        assertThat(rsl1, is(40));
        Integer rs2 = stack.poll();
        assertThat(rs2, is(50));
        stack.poll();


    }


}