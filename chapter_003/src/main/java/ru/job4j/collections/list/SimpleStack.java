package ru.job4j.collections.list;

import ru.job4j.collections.list.NewNode;

import java.util.NoSuchElementException;


public class SimpleStack<T> extends LinkedContainer {

    public void push(T value) {
        super.addToBeginning(value);
    }

    public T poll() throws NoSuchElementException {
        return (T) super.poll();
    }
}