package ru.job4j.collections.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {

    int position = 0;
    Object[] objects;
    int length;

    public SimpleArrayIterator(Object[] objects) {
        this.objects = objects;
        this.length = objects.length;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        if (position < length) {
            if (objects[position] != (null)) {
                rsl = true;
            }
        }
        return rsl;
    }


    @Override
    public T next() throws NoSuchElementException {
        if (hasNext()) {
            return (T) objects[position++];
        } else {
            throw new NoSuchElementException("нет значений");
        }
    }
}
