package ru.job4j.Collections.Generic;

import static javafx.scene.input.KeyCode.T;

public abstract class AbstractStore<T extends Base> implements Store{

    public SimpleArray<T> array = new SimpleArray<>(100);

    @Override
    public void add(Base model) {
        array.add((T) model);
    }


    @Override
    public boolean replace(String id, Base model) {
        int ind = searchIndexById(id);
        if (ind >= 0) {
            array.set(ind, (T) model);
            return true;
        }
        return false;
    }


    @Override
    public boolean delete(String id) {
        int ind = searchIndexById(id);
        if (ind >= 0) {
            array.delete(ind);
            return true;
        }
        return false;
    }


    @Override
    public Base findById(String id) {
        int ind = searchIndexById(id);
        if (ind >= 0) {
            return array.get(ind);
        }

        return null;
    }


    public int searchIndexById(String id) {
        int rsl = -1;
        for (int i = 0; i < this.array.getSize(); i++) {
            if (array.get(i) != null && array.get(i).getId().equals(id)) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }


    public T getObject(int index) {
        return array.get(index);
    }








}



