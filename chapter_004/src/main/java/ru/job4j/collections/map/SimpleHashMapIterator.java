package ru.job4j.collections.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMapIterator<K, V> implements Iterator {
    private Cell<K, V>[] hashTable;
    private int size;
    private int modCount;
    private int[] expectedCount;
    int position = 0;

    public SimpleHashMapIterator(Cell<K, V>[] hashTable, int[] expectedCount) {
        this.hashTable = hashTable;
        this.size = hashTable.length;
        this.expectedCount = expectedCount;
        this.modCount = expectedCount[0];
    }

    public void checkForModif() throws ConcurrentModificationException {
        if (expectedCount[0] != this.modCount) {
            throw new ConcurrentModificationException("Контейнер модифицирован");
        }
    }


    @Override
    public boolean hasNext() {
        checkForModif();
        for (int i = position; i < this.size; i++) {
            if (this.hashTable[i] != null) {
                this.position = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public V next() throws NoSuchElementException {
        if (hasNext()) {
            return hashTable[position++].data;
        } else {
            throw new NoSuchElementException("нет значений");
        }
    }
}
