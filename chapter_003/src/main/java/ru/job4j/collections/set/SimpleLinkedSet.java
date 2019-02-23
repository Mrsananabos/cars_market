package ru.job4j.collections.set;

import ru.job4j.collections.list.LinkedContainer;
import ru.job4j.collections.list.Node;

public class SimpleLinkedSet<T> extends LinkedContainer<T> {

    public void add(T value) {
        boolean rsl = false;
        if (super.head != null) {
            Node cursor = super.head;
            do {
                if (cursor.data.equals(value)) {
                    rsl = true;
                    break;
                } else {
                    cursor = cursor.next;
                }
            } while (cursor != super.head);
        }
        if (!rsl) {
            super.add(value);
        }
    }


}
