package ru.job4j.collections.tree;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.job4j.collections.iterators.MatrixIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ElementaryTreeTest {
    @Test
    public void when6ElFindLastThen6() {
        ElementaryTree<Integer> tree = new ElementaryTree<Integer>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        ElementaryTree<Integer> tree = new ElementaryTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenTreeDontAddDuplicates() {
        ElementaryTree<Integer> tree = new ElementaryTree<>(1);
        boolean rsl = tree.add(1, 10);
        assertThat(rsl, Matchers.is(true));
        boolean rsl1 = tree.add(1, 20);
        assertThat(rsl1, Matchers.is(true));
        boolean rsl2 = tree.add(10, 20);
        assertThat(rsl2, Matchers.is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        ElementaryTree<Integer> tree = new ElementaryTree<Integer>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(3, 8);
        tree.add(3, 89);
        tree.add(5, 6);
        Iterator it = tree.iterator();
        assertThat(it.next(), Matchers.is(1));
        assertThat(it.next(), Matchers.is(2));
        assertThat(it.next(), Matchers.is(3));
        assertThat(it.next(), Matchers.is(4));
        assertThat(it.next(), Matchers.is(8));
        assertThat(it.next(), Matchers.is(89));
        assertThat(it.next(), Matchers.is(5));
        assertThat(it.next(), Matchers.is(6));
        it.next();

    }


    @Test
    public void whenTreeisBinary() {
        ElementaryTree<Integer> tree = new ElementaryTree<Integer>(1);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(3, 8);
        tree.add(3, 89);
        tree.add(89, 899);
        tree.add(5, 6);
        tree.add(89, 6);
        tree.add(5, 879);
        assertThat(tree.isBinary(), Matchers.is(true));
    }

    @Test
    public void whenTreeisNotBinary() {
        ElementaryTree<Integer> tree = new ElementaryTree<Integer>(1);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(3, 8);
        tree.add(3, 89);
        tree.add(89, 899);
        tree.add(5, 6);
        tree.add(89, 6);
        tree.add(5, 879);
        tree.add(5, 456);
        assertThat(tree.isBinary(), Matchers.is(false));
    }


}