package ru.job4j.collections.map;

import ru.job4j.collections.set.CellForHashTable;

import java.util.Iterator;

public class SimpleHashMap<K, V> implements Iterable {
    public Cell<K, V>[] hashTable;
    private int size = 10;
    private int position = 0;
    public float capacity = 0;
    public int[] modCoun = new int[]{0};

    public SimpleHashMap() {
        this.hashTable = new Cell[this.size];
    }


    public boolean insert(K key, V value) {
        if (this.capacity >= 0.69) {
            enlargeContainer();
        }
        boolean rsl = false;
        int hash = key.hashCode();
        int currentInd = hash % this.hashTable.length;
        if (hashTable[currentInd] == null) {
            hashTable[currentInd] = new Cell<>(key, value);
            this.position++;
            rsl = true;
        } else {
            if ((hashTable[currentInd].key.equals(key))) {
                hashTable[currentInd].data = value;
                rsl = true;
            }
        }
        this.capacity = (float) this.position / (float) this.size;
        return rsl;
    }


    public void enlargeContainer() {
        Cell<K, V>[] newHashTable = new Cell[this.size * 2];
        this.size = this.size * 2;
        for (Cell<K, V> cell : this.hashTable) {
            if (cell != null) {
                int hash = cell.key.hashCode();
                int currentInd = hash % this.size;
                newHashTable[currentInd] = cell;
            }
        }
        this.hashTable = newHashTable;
        this.modCoun[0]++;
    }

    public V get(K key) {
        V result = null;
        int hash = key.hashCode();
        int ind = hash % this.hashTable.length;
        if (ind <= this.size) {
            result = this.hashTable[ind].data;
        }
        return result;
    }

    boolean delete(K key) {
        boolean result = false;
        int hash = key.hashCode();
        int index = hash % this.hashTable.length;
        if (this.hashTable[index] != null && this.hashTable[index].key.equals(key)) {
            this.hashTable[index] = null;
            this.position--;
            this.modCoun[0]++;
            this.capacity = (float) this.position / (float) this.size;
            result = true;
        }
        return result;
    }


    @Override
    public Iterator iterator() {
        return new SimpleHashMapIterator<K, V>(this.hashTable, this.modCoun);
    }


}