package ru.job4j.Collections.List;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorOfLinkedContainer<T> implements Iterator {


    public Node cursor;
    public Node head;
    public Node cursor1;
    int number = 0;
    private int modCount;
    public int[] expectedCount;

    public IteratorOfLinkedContainer(Node head, int[] expectedCount){
        this.head=head;
        this.cursor = head;
        this.modCount = expectedCount[0];
        this.expectedCount = expectedCount;
    }

    public void checkForModif() throws ConcurrentModificationException {
        if (this.expectedCount[0] != this.modCount) throw new ConcurrentModificationException("Контейнер модифицирован");
    }



    @Override
    public boolean hasNext() throws ConcurrentModificationException {
        checkForModif();
        this.cursor1 = this.cursor;
        return cursor != null && (this.number <= 0 || cursor != this.head);
    }


    @Override
    public T next() throws NoSuchElementException {
        if (hasNext()) {
            cursor = cursor.next;
            this.number++;
            return (T) cursor1.data;
        } else throw new NoSuchElementException("нет значений");
    }
}
