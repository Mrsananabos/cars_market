package ru.job4j.Collections.List;

import java.util.NoSuchElementException;

public class SimpleQueue<T> extends LinkedContainer{

    Node returnedNode;


    public void push(T value){
        super.add(value);
    }

    public T poll() throws NoSuchElementException {
        if (super.head == null) throw new NoSuchElementException("Queue пуст");
        this.returnedNode = super.head;
        super.deleteHead();
        return (T) returnedNode.data;
    }

}
