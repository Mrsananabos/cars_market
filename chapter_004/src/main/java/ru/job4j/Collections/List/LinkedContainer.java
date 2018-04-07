package ru.job4j.Collections.List;

import java.util.Iterator;

public class LinkedContainer<T> implements Iterable<T>{
    private Node<T> head;
    private Node<T> next;
    private int[] modCoun = new int[] {0};



    public void add(T value){
        Node a = new Node();
        a.data=value;

        if(head == null){
            head = a;
            head.next = head;
        } else {
            next.next = a;
            a.next = head;
        }
        this.next = a;
        this.modCoun[0]++;
    }


    public T getData(int index){
        if (index == 0) return (T) head;
        Node rsl = head;
        for (int i = 1;i<=index;i++){
            rsl = rsl.next;
        }
        return (T) rsl.data;
    }


    @Override
    public Iterator<T> iterator() {
        return new IteratorOfLinkedContainer<T>(this.head, this.modCoun);}

    public static void main(String[] args) {
        LinkedContainer<Integer> container = new LinkedContainer<Integer>();

        container.add(1);
        container.add(2);
        container.add(3);

        Iterator it = container.iterator();
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());

    }
}
