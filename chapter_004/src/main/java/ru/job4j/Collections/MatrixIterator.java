package ru.job4j.Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator{

    public int[][] array;
    int currentIndex = 0;
    int currentColumn=0;
    int column;
    int index;


    public MatrixIterator(int[][] array) throws NoSuchElementException{
        this.array=array;
        if (array.length == 0) throw new NoSuchElementException("нет значений");
        this.column=array.length;
        this.index=array[0].length;

    }


    @Override
    public boolean hasNext() {
        return (currentIndex<index) ||
                (currentIndex==index && currentColumn<(column-1));
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (currentIndex>=index){
            currentIndex=0;
            currentColumn++;
            if (currentColumn==column) throw new NoSuchElementException("нет значений");
            this.index=array[currentColumn].length;
        }

        return array[currentColumn][currentIndex++] ;
    }

}
