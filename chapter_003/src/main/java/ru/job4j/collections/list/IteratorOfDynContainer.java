package ru.job4j.collections.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collections.generic.SimpleArray;

import javax.swing.text.html.HTMLDocument;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
@ThreadSafe
public class IteratorOfDynContainer<T> implements Iterator {

    @GuardedBy("this")
    public Object[] container;
    private int length;
    public int index = 0;
    private int modCount;
    private int[] expectedCount;

    public IteratorOfDynContainer(Object[] cont, int[] expectedCount) {
        this.container = cont;
        this.modCount = expectedCount[0];
        this.expectedCount = expectedCount;
        this.length = cont.length;
    }

    public synchronized void checkForModif() throws ConcurrentModificationException {
        if (expectedCount[0] != this.modCount) {
            throw new ConcurrentModificationException("Контейнер модифицирован");
        }
    }


    @Override
    public synchronized boolean hasNext() throws ConcurrentModificationException {
        checkForModif();
        if ((index < length) && (container[index] != null)) {
            return true;
        }
        return false;
    }


    @Override
    public synchronized Object next() throws NoSuchElementException {
        if (hasNext()) {
            return container[index++];
        } else {
            throw new NoSuchElementException("нет значений");
        }
    }

}
