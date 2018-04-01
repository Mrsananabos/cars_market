package ru.job4j.Collections.List;

import java.util.Iterator;

public class LinkedContainer<T> implements Iterable<T>{


    public Node[] container;
    public int index = 0;
    public int size;
    public int[] modCount = new int[]{0};


    public LinkedContainer(int size){
        this.container=new Node[size];
        this.size = size;
    }


    public int getSize(){return this.size;}



    public void enlargeContainer(){
        Node[] newContainer = new Node[size*2];
        System.arraycopy(this.container,0, newContainer, 0, this.size);
        this.size = this.size * 2;
        this.container = newContainer;
        this.modCount[0]++;
    }

    public void add(T value){
        if (this.index==this.size) {enlargeContainer();}

        Node newN = new Node(value, this.container[0]);

        if (this.index == 0) { this.container[0] = newN;}
             else {
            this.container[index] = newN;
            this.container[index-1].setNext(newN);}

        this.index++;
    }


    public T getData(int index){
        return (T) this.container[index].getData();
    }


    @Override
    public Iterator<T> iterator() {
        return new IteratorOfLinkedContainer<T>(this.container, this.modCount);
    }

    public static void main(String[] args) {
        LinkedContainer<Integer> container = new LinkedContainer<>(3);
        container.add(1);
        container.add(2);

        Iterator it = container.iterator();

        System.out.println(it.next());
        System.out.println(it.next());container.add(4);
        System.out.println(it.next());
        System.out.println(it.next());


    }
}
