package ru.job4j.Collections.List;
import ru.job4j.Collections.List.NewNode;

import java.util.NoSuchElementException;


public class SimpleStack<T> extends LinkedContainer {
    Node returnedNode;


    public void push(T value) {
        super.add(value);
    }



    public T poll() throws NoSuchElementException {
        if (super.tail == null) {
            throw new NoSuchElementException("Stack пуст");
        }
        this.returnedNode=super.tail;
        super.deleteTail();
        return (T) returnedNode.data;
    }
}