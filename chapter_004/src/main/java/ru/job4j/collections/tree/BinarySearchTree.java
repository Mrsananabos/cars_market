package ru.job4j.collections.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class BinarySearchTree<E extends Comparable<E>> implements Iterable {

    public NewNode<E> root;
    private NewNode<E> parent;
    private NewNode<E> child;
    private boolean recursion = false;


    public void add(E value) {
        boolean inCycle = true;
        this.parent = this.recursion ? this.parent : this.root;
        if (this.root == null) {
            this.root = new NewNode<>(value);
            inCycle = false;
        }
        while (inCycle) {
            int result = value.compareTo(this.parent.getValue());
            if (result > 0) {
                this.child = this.parent.getRight();
            } else {
                if (result < 0) {
                    this.child = this.parent.getLeft();
                }
            }
            if (this.child != null) {
                this.parent = this.child;
                this.recursion = true;
                this.add(value);
            } else {
                if (result == 1) {
                    this.parent.setRight(new NewNode<>(value));
                    inCycle = false;
                    this.recursion = false;
                } else {
                    this.parent.setLeft(new NewNode<>(value));
                    inCycle = false;
                    this.recursion = false;
                }
            }

        }


    }


    @Override
    public Iterator<E> iterator() {
        return new IteratorOfBinarySearchTree<>(this.root);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(10);
        tree.add(16);
        tree.add(20);

        System.out.println(tree.root.getValue());
        System.out.println(tree.root.getRight().getValue());
        System.out.println(tree.root.getLeft().getValue());
    }

}
