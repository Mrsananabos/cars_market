package ru.job4j.collections.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class BinarySearchTree<E extends Comparable<E>> implements Iterable {

    public NewNode<E> root;


    public void add(E value) {
        NewNode<E> parent = this.root;
        NewNode<E> child;
        boolean inCycle = true;
        if (this.root == null) {
            this.root = new NewNode<>(value);
            inCycle = false;
        }
        while (inCycle) {
            int result = value.compareTo(parent.getValue());
            child = result > 0 ? parent.getRight() : parent.getLeft();
            if (child == null) {
             if (result > 0) {
                 parent.setRight(new NewNode<>(value));
                 inCycle = false;
             } else {
                 parent.setLeft(new NewNode<>(value));
                 inCycle = false;
             }
            } else {
                parent = child;
            }
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new IteratorOfBinarySearchTree<>(this.root);
    }


}
