package ru.job4j.collections.tree;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class IteratorOfElementaryTree<E extends Comparable<E>> implements Iterator {

    List<Node<E>> current;
    Iterator itOfCurrent;


    public IteratorOfElementaryTree(Node<E> root) {
        this.current = new ArrayList<>(Arrays.asList(root));
        this.itOfCurrent = this.current.iterator();
    }


    private boolean findNext() {
        List<Node<E>> data = new ArrayList<>();
        Iterator newItOfCurrent = this.current.iterator();
        while (newItOfCurrent.hasNext()) {
            Node<E> element = (Node<E>) newItOfCurrent.next();
            for (Node child : element.leaves()) {
                data.add(child);
            }
        }

        this.itOfCurrent = data.iterator();
        if (!itOfCurrent.hasNext()) {
            return false;
        }

        this.current = data;
        return true;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        if (this.itOfCurrent.hasNext()) {
            rsl = true;
        } else {
            rsl = findNext();
        }

        return rsl;
    }

    @Override
    public E next() throws NoSuchElementException {
        if (hasNext()) {
            Node<E> result = (Node<E>) this.itOfCurrent.next();
            return result.getValue();
        }

        throw new NoSuchElementException("нет значений");
    }
}
