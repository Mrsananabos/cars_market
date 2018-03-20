package ru.job4j.Collections.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {

    public int[] array;
    int index = 0;
     int length;
     int nextPosition = 0;


    public EvenIterator(final int[] numbers) {

        this.array=numbers;
        this.length=numbers.length;
    }


        @Override
    public boolean hasNext() {
         for (int i=index; i<length; i++){
             if (array[i] % 2 == 0) {
                 nextPosition = i;
                 return true;
             }
         }

            return false;
    }



    @Override
    public Object next() throws NoSuchElementException {
        if (hasNext()) {
            index=nextPosition;
            return array[index++];
        } else {
            throw new NoSuchElementException("нет значений");

        }
    }


}
