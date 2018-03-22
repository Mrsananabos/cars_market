package ru.job4j.Collections.Iterators;

import javax.swing.text.html.HTMLDocument;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    int newLine = 0;
    int action = 0;

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            public Iterator<Integer> current = it.next();


            @Override
            public boolean hasNext() {
                boolean rsl = false;
                if (current.hasNext()) {
                    newLine = 0;
                    rsl = true;
                } else {
                    if (action>0 && it.hasNext()) {
                        newLine = 1;
                        rsl = true;
                    }
                }
                return rsl;
            }



            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("нет значений");
                } else {
                    if (newLine == 1) {
                        current = it.next();
                    }
                    action++;
                    return current.next();
                }
            }

        };
    }

}