package ru.job4j.Collections.List;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class NodeTest {
    @Test
    public void whenTheLisIsCyclicalThenTrue() {

        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        boolean rsl = first.hasCycle(first);
        assertThat(rsl, is(true));
    }


    @Test
    public void whenTheLisIsNotCyclicalThenFalse() {

        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        boolean rsl = first.hasCycle(first);
        assertThat(rsl, is(false));
    }

}