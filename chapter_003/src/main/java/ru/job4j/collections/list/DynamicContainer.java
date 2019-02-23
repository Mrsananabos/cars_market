package ru.job4j.collections.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collections.generic.SimpleArray;
import ru.job4j.collections.generic.SimpleArrayIterator;

import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


@ThreadSafe
public class DynamicContainer<E> implements Iterable {

    @GuardedBy("this")
    protected Object[] container;
    private int size;
    protected int[] modCoun = new int[]{0};
    protected int index = 0;


    public DynamicContainer(int size) {
        this.container = new Object[size];
        this.size = size;
    }


    public synchronized int getSize() {
        return this.size;
    }


    public synchronized void enlargeContainer() {
        Object[] newContainer = new Object[this.size * 2];
        System.arraycopy(this.container, 0, newContainer, 0, this.size);
        this.size = this.size * 2;
        this.container = newContainer;
        this.modCoun[0]++;
    }


    public synchronized void add(E value) {
        if (this.index == size) {
            enlargeContainer();
        }
        this.container[index++] = value;
    }


    public synchronized E get(int index) {
        return (E) this.container[index];
    }


    @Override
    public synchronized Iterator<E> iterator() {
        return new IteratorOfDynContainer(this.container, this.modCoun);
    }


}
