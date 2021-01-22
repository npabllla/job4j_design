package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addHead(T value) {
        head = new Node<>(value, head);
    }

    public T deleteFirst() {
        Node<T> node = new Node<>(null, null);
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            node.value = head.value;
            head = head.next;
        }
        return node.value;
    }

    public T deleteLast() {
        Node<T> temp = new Node<>(null, null);
        Node<T> tail = head;
        Node<T> prevTail = head;
        if (prevTail.next != null && prevTail.next.next != null) {
            while (prevTail.next.next != null) {
                prevTail = prevTail.next;
            }
            temp.value = prevTail.next.value;
            prevTail.next = null;
            return temp.value;
        }
        while (tail.next != null) {
            tail = tail.next;
        }
        temp.value = tail.value;
        tail.next = null;
        return temp.value;
    }

    public void revert() {
        Node<T> node = null;
        Node<T> temp = head;
        while (temp != null) {
            Node<T> next = temp.next;
            temp.next = node;
            node = temp;
            temp = next;
        }
        head = node;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}