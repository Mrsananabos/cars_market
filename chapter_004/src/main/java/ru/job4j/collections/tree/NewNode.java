package ru.job4j.collections.tree;


public class NewNode<E extends Comparable<E>> {

    private NewNode<E> left;
    private NewNode<E> right;
    private final E value;


    public NewNode(final E value) {
        this.left = null;
        this.right = null;
        this.value = value;
    }


    public E getValue() {
        return this.value;
    }

    public void setLeft(NewNode<E> left) {
        this.left = left;
    }

    public void setRight(NewNode<E> right) {
        this.right = right;
    }

    public NewNode<E> getLeft() {
        return left;
    }

    public NewNode<E> getRight() {
        return right;
    }



}



