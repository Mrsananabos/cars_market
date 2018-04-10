package ru.job4j.Collections.List;
import ru.job4j.Collections.List.NewNode;

import java.util.NoSuchElementException;


public class SimpleStack<T> extends LinkedContainer {

    public void push(T value) {
        super.addToBeginning(value);
    }

    public T poll() throws NoSuchElementException {
        return (T) super.poll();
    }
}