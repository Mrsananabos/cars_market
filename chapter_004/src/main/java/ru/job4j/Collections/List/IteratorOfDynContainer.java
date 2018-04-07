package ru.job4j.Collections.List;

import ru.job4j.Collections.Generic.SimpleArray;

import javax.swing.text.html.HTMLDocument;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorOfDynContainer implements Iterator {


    public Object[] container;
    public int length;
    public int index=0;
    private int modCount;
    public int[] expectedCount;

    public IteratorOfDynContainer(Object[] cont, int[] expectedCount){
        this.container = cont;
        this.modCount = expectedCount[0];
        this.expectedCount = expectedCount;
        this.length=cont.length;
    }

    public void checkForModif() throws ConcurrentModificationException{
        if (expectedCount[0] != this.modCount) throw new ConcurrentModificationException("Контейнер модифицирован");
    }






    @Override
    public boolean hasNext() throws ConcurrentModificationException {
        checkForModif();
        if ((index < length) && (container[index] != null)) {
            return true;
        }
        return false;
    }


    @Override
    public Object next() throws NoSuchElementException {
            if (hasNext()) {
                return container[index++];
            } else throw new NoSuchElementException("нет значений");
        }

}