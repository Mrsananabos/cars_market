package ru.job4j.Collections.Set;

import ru.job4j.Collections.List.LinkedContainer;
import ru.job4j.Collections.List.Node;

import java.util.Iterator;

public class SimpleLinkedSet<T> extends LinkedContainer<T> {

    public void add(T value){
        boolean rsl = false;
        if (super.head != null){
            Node cursor = super.head;
            do {
                if (cursor.data.equals(value)) {
                    rsl = true;
                    break;
                }
                    else {
                    cursor=cursor.next;
                }
            } while (cursor!=super.head);
        } 
        if (!rsl){
        super.add(value);}
    }


}