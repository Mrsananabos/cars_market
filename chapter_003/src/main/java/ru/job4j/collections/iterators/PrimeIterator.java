package ru.job4j.collections.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {
    private int[] array;
    private int index = 0;
    private int number = 0;
    private int length;
    private float k1;
    private float k2;

    public PrimeIterator(int[] array) {
        this.array = array;
        this.length = array.length;
    }

    public int searchPrimeNumber() {
        float six = 6;
        float one = 1;
        if (index != number) index = number;
        for (int i = index; i < length; i++) {
            k1 = (array[i] - one) / six;
            k2 = (array[i] + one) / six;
            if (((k1 % 1 == 0) || (k2 % 1 == 0) || (array[i] == 3) || (array[i] == 2)) && (array[i]) > 1) {
                return i;
            }

        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        int rsl = searchPrimeNumber();
        return rsl != -1;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (hasNext()) {
            number = searchPrimeNumber() + 1;
            return array[number - 1];
        } else {
            throw new NoSuchElementException("нет значений");

        }
    }
}
