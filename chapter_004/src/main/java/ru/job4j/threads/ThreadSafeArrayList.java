package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class ThreadSafeArrayList<E> implements Iterable {


    @GuardedBy("this")
    protected Object[] container;
    private int size;
    protected int[] modCoun = new int[]{0};
    protected int index = 0;


    public ThreadSafeArrayList(int size) {
        this.container = new Object[size];
        this.size = size;
    }


    public int getSize() {
        synchronized (this) {
            return this.index;
        }
    }


    private void enlargeContainer() {
        Object[] newContainer = new Object[this.size * 2];
        System.arraycopy(this.container, 0, newContainer, 0, this.size);
        this.size = this.size * 2;
        this.container = newContainer;
        this.modCoun[0]++;
    }


    public synchronized void add(E value) {
        synchronized (this) {
            if (this.index == size) {
                enlargeContainer();
            }
            this.container[index++] = value;
        }
    }


    public E get(int index) {
        synchronized (this) {
            return (E) this.container[index];
        }
    }


    @Override
    public Iterator<E> iterator() {
        synchronized (this) {
            return new ThreadSafeIteratorOfArrayList(this.container, this.modCoun);
        }
    }
}


