package ru.job4j.collections.set;

import javax.jws.HandlerChain;

public class SetHashTable<T> {
    public CellForHashTable<T>[] hashTable;
    public int index = 0;
    private int size;

    public SetHashTable(int size) {
        this.hashTable = new CellForHashTable[size];
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public void add(T value) {
        if (this.index == this.size) {
            enlargeContainer();
        }
        int hash = value.hashCode();
        hashTable[index++] = new CellForHashTable<>(hash, value);
    }

    public void enlargeContainer() {
        CellForHashTable<T>[] newHashTable = new CellForHashTable[this.size * 2];
        System.arraycopy(this.hashTable, 0, newHashTable, 0, this.size);
        this.size = this.size * 2;
        this.hashTable = newHashTable;
    }


    public boolean contains(T value) {
        int rsl = searchValue(value);
        return (rsl >= 0);
    }


    public boolean remove(T value) {
        int ind = searchValue(value);
        if (ind == -1) {
            return false;
        }
        System.arraycopy(this.hashTable, ind + 1, this.hashTable, ind, this.index - ind);
        this.hashTable[index - 1] = null;
        index--;
        return true;

    }

    private int searchValue(T value) {
        int hash = value.hashCode();
        for (int i = 0; i < index; i++) {
            if (hashTable[i].hash == hash && hashTable[i].data.equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
