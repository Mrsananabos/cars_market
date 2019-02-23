package ru.job4j.collections.set;

import ru.job4j.collections.list.DynamicContainer;

public class SimpleSet<T> extends DynamicContainer<T> {

    public SimpleSet(int size) {
        super(size);
    }

    public void add(T value) {
        boolean rsl = false;
        for (int i = 0; i < super.index; i++) {
            if (value.equals(super.container[i])) {
                rsl = true;
            }
        }
        if (!rsl) {
            super.add(value);
        }
    }

}


