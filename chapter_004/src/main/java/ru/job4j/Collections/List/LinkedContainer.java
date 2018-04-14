package ru.job4j.Collections.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedContainer<T> implements Iterable<T>{
    protected Node<T> head;
    protected Node<T> tail;
    private int[] modCoun = new int[] {0};



    public void add(T value){
        Node<T> a = new Node<>(value);

        if(head == null){
            this.head = a;
            this.tail=a;
            this.head.next = this.tail;
        } else {
            this.tail.next = a;
            a.next = this.head;
            a.previous=this.tail;
            this.head.previous=a;
        }
        this.tail = a;
        this.modCoun[0]++;
    }


    public void addToBeginning(T value) {
        Node<T> a = new Node<>(value);
        if (head == null) {
            this.head = a;
            this.tail = a;
            this.head.next = this.tail;
            this.head.previous = this.tail;
        } else {
            a.next = this.head;
            this.head.previous = a;
            a.previous = this.tail;
            this.tail.next = a;
            this.head = a;
        }
    }


    public T getHead(){return this.head.data;}

    public T getTail(){return this.tail.data;}


    public T getData(int index){
        if (index == 0) return (T) head;
        Node rsl = head;
        for (int i = 1;i<=index;i++){
            rsl = rsl.next;
        }
        return (T) rsl.data;
    }


    public void deleteByIndex(int index){
        if (index == 0) {deleteHead();}
        Node rsl = head;
        for (int i = 1;i<=index;i++){
            rsl = rsl.next;
        }
        rsl.previous.next=rsl.next;
        rsl.next.previous=rsl.previous;

    }


    public void deleteHead(){
        if (this.head.next==this.head){
            this.head = null;
            return;
        }
        this.tail.next=this.head.next;
        this.head.next.previous=this.tail;
        this.head=this.head.next;
    }

    public void deleteTail(){
        this.tail.previous.next=this.head;
        this.head.previous=this.tail.previous;
        this.tail=this.tail.previous;
    }

    public T poll() throws NoSuchElementException {
        if (this.head == null) {
        throw new NoSuchElementException("Нет элементов");
    }
        Node<T> returnedNode = this.head;
        this.deleteHead();
        return (T) returnedNode.data;
    }





    @Override
    public Iterator<T> iterator() {
        return new IteratorOfLinkedContainer<T>(this.head, this.modCoun);}


}
