package ru.job4j.Collections.List;

import ru.job4j.Collections.Generic.SimpleArray;
import ru.job4j.Collections.Generic.SimpleArrayIterator;

import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicContainer<E> implements Iterable {


    private Object[] container;
    private int size;
    private int modCoun = 0;
    private int expectedCount = 0;
    public int index = 0;


    public DynamicContainer(int size) {
        this.container = new Object[size];
        this.size = size;
    }


    public int getSize() {
        return this.size;
    }


    public void enlargeContainer() {
        Object[] newContainer = new Object[this.size * 2];
        System.arraycopy(this.container, 0, newContainer, 0, this.size);
        this.size = this.size * 2;
        this.container = newContainer;
        this.modCoun++;
    }


    public void add(E value) {
        if (this.index == size) {
            enlargeContainer();
        }
        this.container[index++] = value;
    }


    E get(int index) {
        return (E) this.container[index];
    }

    ;



    @Override
    public Iterator<E> iterator() {
        this.expectedCount = this.modCoun;
        return new Iterator<E>() {

            private int ind = 0;

            public void checkForModif() throws ConcurrentModificationException{
                if (expectedCount != modCoun) throw new ConcurrentModificationException("Контейнер модифицирован");
            }

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                checkForModif();
                if ((ind < size) && (container[ind] != null)) {
                    return true;
                }
                return false;
            }


            @Override
            public E next() throws NoSuchElementException {
                if (hasNext()) {
                    return (E) container[ind++];
                } else throw new NoSuchElementException("нет значений");
            }
        };
    }




}
