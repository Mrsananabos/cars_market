package ru.job4j.collections.map;

public class Cell<K, V> {
    protected K key;
    protected V data;

    public Cell(K key, V data) {
        this.key = key;
        this.data = data;
    }

    public V getData() {
        return this.data;
    }
}
