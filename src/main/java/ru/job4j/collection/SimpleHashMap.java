package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<K> {
    private int length = 16;
    private int size = 0;
    private Node<K, V>[] couples = new Node[length];

    public boolean insert(K key, V value) {
        if (contains(key)) {
            return false;
        }
        if (size == length) {
            length *= 2;
            couples = Arrays.copyOf(couples, length);
        }
        couples[size] = new Node<>(key, value);
        size++;
        return true;
    }

    public V get(K key) {
        if (contains(key)) {
            for (int i = 0; i < size; i++) {
                if (couples[i].key.equals(key)) {
                    return couples[i].value;
                }
            }
            return null;
        } else {
            throw new NullPointerException();
        }
    }

    public boolean delete(K key) {
        if (contains(key)) {
            for (int i = 0; i < size; i++) {
                if (couples[i].key.equals(key)) {
                    couples[i].key = null;
                    couples[i].value = null;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    Iterator<K> iterator = new Iterator<>() {
        private int point = 0;
        @Override
        public boolean hasNext() {
            return point < size;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return couples[point++].key;
        }
    };
    @Override
    public Iterator<K> iterator() {
        return iterator;
    }

    private boolean contains(K key) {
        for (int i = 0; i < size; i++) {
            if (couples[i].key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    private static class Node<K, V> {
        K key;
        V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
