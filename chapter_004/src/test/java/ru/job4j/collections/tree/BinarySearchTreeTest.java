package ru.job4j.collections.tree;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void whenBinarySearchTreeAddedNewNodeRight() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(10);
        tree.add(8);
        tree.add(20);
        tree.add(8);
        tree.add(18);
        tree.add(31);
        tree.add(9);
        Iterator it = tree.iterator();
        assertThat(it.next(), is(10));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(20));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(9));
        assertThat(it.next(), is(18));
        assertThat(it.next(), is(31));
    }

    @Test(expected = NoSuchElementException.class)
    public void noSuchElementException() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(10);
        tree.add(8);
        tree.add(20);
        tree.add(8);
        tree.add(18);
        tree.add(31);
        tree.add(9);
        Iterator it = tree.iterator();
        assertThat(it.next(), is(10));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(20));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(9));
        assertThat(it.next(), is(18));
        assertThat(it.next(), is(31));
        it.next();
    }

}