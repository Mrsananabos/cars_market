package ru.job4j.Collections.Set;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedSetTest {

    @Test
    public void whenAddSameValuesOfObjects(){
    SimpleLinkedSet<Integer> s = new SimpleLinkedSet<>();
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

    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowConcurrentModificationException() {
        SimpleLinkedSet<Integer> s = new SimpleLinkedSet<>();
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