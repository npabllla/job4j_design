package ru.job4j.tree;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            if (data.poll().children.size() > 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> temp = findBy(parent);
         if (findBy(child).isPresent()) {
            return false;
        } else if (temp.isPresent()) {
            temp.get().children.add(new Node<>(child));
            return true;
        }
         return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}