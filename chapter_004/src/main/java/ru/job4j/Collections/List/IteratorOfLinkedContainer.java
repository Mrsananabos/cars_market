package ru.job4j.Collections.List;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorOfLinkedContainer<T> implements Iterator {

    public Node[] container;
    public int length;
    public int index=0;
    private int modCount;
    public int[] expectedCount;

    public IteratorOfLinkedContainer(Node[] cont, int[] expectedCount){
        this.container = cont;
        this.modCount = expectedCount[0];
        this.expectedCount = expectedCount;
        this.length=cont.length;
    }

    public void checkForModif() throws ConcurrentModificationException {
        if (this.expectedCount[0] != this.modCount) throw new ConcurrentModificationException("Контейнер модифицирован");
    }






    @Override
    public boolean hasNext() throws ConcurrentModificationException {
        checkForModif();
        if ((index < length) && (container[index].getData() != null)) {
            return true;
        }
        return false;
    }


    @Override
    public T next() throws NoSuchElementException {
        if (hasNext()) {
            return (T) container[index++].getData();
        } else throw new NoSuchElementException("нет значений");
    }
}
