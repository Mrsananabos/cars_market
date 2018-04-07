package ru.job4j.Collections.List;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    Node returnedNode;
    Node headNode;
    Node tailNode;

    public void push(T value){
        Node a = new Node();
        a.data = value;

        if (this.headNode == null){
            this.headNode=a;
            this.tailNode=a;
            this.headNode.next=this.headNode;
        } else {
            this.tailNode.next=a;
            a.next=this.headNode;
            this.tailNode=a;
        }
    }

    public T poll() throws NoSuchElementException {
        if (this.headNode == null) throw new NoSuchElementException("Queue пуст");
        this.returnedNode = this.headNode;
        if (this.tailNode==this.headNode){
            this.headNode = null;
        } else {
            this.headNode=this.headNode.next;

        }
        return (T) returnedNode.data;

    }

}
