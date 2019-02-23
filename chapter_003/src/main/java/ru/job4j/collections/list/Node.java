package ru.job4j.collections.list;

public class Node<T> {

    public T data;
    public Node next;
    public Node previous;

    public Node(T value) {
        this.data = value;
    }

    boolean hasCycle(Node first) {
        if (first == null) {
            return false;
        }
        Node slowCursor = first;
        Node fastCursor = first.next;
        while (fastCursor != null) {
            if (fastCursor.equals(slowCursor)) {
                return true;
            } else if (fastCursor.next == null) {
                return false;
            } else {
                slowCursor = slowCursor.next;
                fastCursor = fastCursor.next.next;
            }
        }
        return false;
    }


}
