package ru.job4j.Collections;

import java.util.Iterator;

public class MatrixIterator implements Iterator{

    public int[][] array;
    int currentIndex = 0;
    int currentColumn=0;
    int column=array.length;
    int index=array[0].length;


    public MatrixIterator(int[][] array){
        this.array=array;
    }


    @Override
    public boolean hasNext() {
        return (currentIndex<index && currentColumn<column);
    }

    @Override
    public Object next() {
        if (currentIndex>index){
            currentIndex=0;
            currentColumn++;
            index=array[column].length;
        }
        return array[currentColumn][currentIndex++] ;
    }

}
