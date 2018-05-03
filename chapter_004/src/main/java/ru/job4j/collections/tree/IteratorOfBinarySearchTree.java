package ru.job4j.collections.tree;

import java.util.*;

public class IteratorOfBinarySearchTree<E extends Comparable<E>> implements Iterator {

    List<NewNode<E>> current;
    Iterator itOfCurrent;


    public IteratorOfBinarySearchTree(NewNode<E> root) {
        this.current = new ArrayList<>(Arrays.asList(root));
        this.itOfCurrent = this.current.iterator();
    }

    private boolean findNext() {
        List<NewNode<E>> data = new ArrayList<>();
        Iterator newItOfCurrent = this.current.iterator();
        while (newItOfCurrent.hasNext()) {
            NewNode<E> element = (NewNode<E>) newItOfCurrent.next();
            if (element.getLeft() != null) {
                data.add(element.getLeft());
            }
            if (element.getRight() != null) {
                data.add(element.getRight());
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
            NewNode<E> result = (NewNode<E>) this.itOfCurrent.next();
            return result.getValue();
        }

        throw new NoSuchElementException("нет значений");
    }


}
