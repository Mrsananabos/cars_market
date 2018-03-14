package ru.job4j.Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {

    public int[] array;
    int index = 0;
     int length;


    public EvenIterator(final int[] numbers) {

        this.array=numbers;
        this.length=numbers.length;
    }


        @Override
    public boolean hasNext() {
         for (int i=index; i<length; i++){
             if (array[i] % 2 == 0) {
                 return true;
             }
         }

            return false;
    }


    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext())throw new NoSuchElementException("нет значений");
            for (int i = index; i < length; i++) {
                if (array[i] % 2 == 0) {
                    index = i + 1;
                    return array[i];
                }
            }
            return null;
    }




}
