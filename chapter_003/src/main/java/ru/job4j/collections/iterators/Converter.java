package ru.job4j.collections.iterators;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {


    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            public Iterator<Integer> current = it.next();


            private Iterator<Integer> findNext() {
                while (it.hasNext()) {
                    current = it.next();
                    if (current.hasNext()) {
                        return current;
                    }
                }
                return null;
            }


            @Override
            public boolean hasNext() {
                if (current.hasNext()) {
                    return true;
                } else {
                    current = findNext();
                }
                return (current != null && current.hasNext());
            }


            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("нет значений");
                } else {
                    return current.next();
                }
            }

        };

    }

}