package ru.job4j.Collections.List;
import ru.job4j.Collections.List.NewNode;

import java.util.NoSuchElementException;


public class SimpleStack<T> {
    NewNode returnedNode;
    NewNode headNode;
    NewNode previousNode;

    public void push(T value) {
        NewNode a = new NewNode();
        a.data = value;

        if (headNode == null) {
            this.headNode = a;
            this.previousNode = a;
            a.nextNode = this.headNode;
            a.prevNode = this.headNode;
        } else {
            this.previousNode.nextNode = a;
            a.nextNode = this.headNode;
            a.prevNode = this.previousNode;
            this.headNode.prevNode = a;
            this.previousNode = a;
        }
    }



    public T poll() throws NoSuchElementException {
        if (this.previousNode == null) {
            throw new NoSuchElementException("Stack пуст");
        }
        if (this.previousNode == this.headNode) {
            this.returnedNode = this.headNode;
            this.previousNode = null;
        } else {
            this.returnedNode = this.previousNode;
            this.previousNode.prevNode.nextNode = this.headNode;
            this.headNode.prevNode = this.previousNode.prevNode;
            this.previousNode = this.headNode.prevNode;
        }

        return (T) returnedNode.data;
    }
}