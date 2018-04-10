package ru.job4j.Collections.List;

import java.util.NoSuchElementException;

public class SimpleQueue<T> extends LinkedContainer{


    public void push(T value){
        super.add(value);
    }

    public T poll() throws NoSuchElementException {
        return (T) super.poll();
    }

}
