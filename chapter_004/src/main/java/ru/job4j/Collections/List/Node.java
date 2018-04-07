package ru.job4j.Collections.List;

public class Node<T> {

    public T data;
    public Node next;



    public Object getData() {return this.data;}
    public Node getNext() {return this.next;}
    public void setData(T data){this.data=data;}
    public void setNext(Node n){this.next=n;}
}
