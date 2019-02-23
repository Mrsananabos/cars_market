package ru.job4j.collections.tree;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.html.Option;
import java.util.*;

public class ElementaryTree<E extends Comparable<E>> implements SimpleTree<E> {

    public Node<E> root;

    public ElementaryTree(E root) {
        this.root = new Node<>(root);
    }


    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> rsl = findBy(parent);
        Optional<Node<E>> rsl1 = findBy(child);
        if (rsl.isPresent() & !rsl1.isPresent()) {
            Node<E> current = rsl.get();
            current.addChild(new Node<>(child));
            return true;
        }

        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E parent) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<Node<E>>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(parent)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<Node<E>>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            List<Node<E>> child = el.leaves();
            int numberChild = child.size();
            if (numberChild > 2) {
                return false;
            }
            for (Node<E> children : child) {
                data.offer(children);
            }
        }
        return true;
    }


    @Override
    public Iterator<E> iterator() {
        return new IteratorOfElementaryTree<E>(this.root);
    }


}
