package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements Iterable<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> last;
    private Node<E> first;


    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Node<E> temp = first;
        int ind = Objects.checkIndex(index, size);
        for (int i = 0; i < ind; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E value = node.item;
                node = node.next;
                return value;
            }
        };
    }
}
