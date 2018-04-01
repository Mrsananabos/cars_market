package ru.job4j.Collections.List;

public class Node<T> {

    private T data;
    private Node next;


    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() {return this.data;}
    public Node getNext() {return this.next;}
    public void setData(T data){this.data=data;}
    public void setNext(Node n){this.next=n;}
}
