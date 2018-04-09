package ru.job4j.Collections.List;

public class Node<T> {

    public T data;
    public Node next;
    public Node previous;

    public Node(T value){
        this.data=value;
    }


}
