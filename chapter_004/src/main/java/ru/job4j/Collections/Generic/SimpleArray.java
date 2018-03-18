package ru.job4j.Collections.Generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {

    public Object[] objects;
    public int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }


    public void add(T value) {
        this.objects[index++] = value;
    }


    public void set(int index, T model){
        this.objects[index] = model;
    }


    public void delete(int ind) {
        for (int i = ind; i<this.objects.length-1; i++ ){
            this.objects[i] = this.objects[i+1];
        }
        index--;
        this.objects[index] = null;
    }


    public T get(int ind){
        return (T) this.objects[ind];
    }


    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<T>(this.objects);
    }


}
