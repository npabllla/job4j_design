package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<K> {
    private int length = 16;
    private int size = 0;
    private Node<K, V>[] couples = new Node[length];

    public boolean insert(K key, V value) {
        int index = hash(key);
        if (contains(index)) {
            return false;
        }
        if (size == length) {
            length *= 2;
            couples = Arrays.copyOf(couples, length);
        }
        couples[index] = new Node<>(key, value);
        size++;
        return true;
    }

    public V get(K key) {
        int index = hash(key);
        if (contains(index)) {
            return couples[index].value;
        }
        return null;
    }

    public boolean delete(K key) {
        int index = hash(key);
        if (contains(index)) {
           couples[index].key = null;
           couples[index].value = null;
           size--;
           return true;
        }
        return false;
    }

    Iterator<K> iterator = new Iterator<>() {
        private int point = 0;
        private int temp = 0;
        @Override
        public boolean hasNext() {
            return point < size;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            for (int i = temp; i < length; i++) {
                if (couples[i] != null) {
                    temp = i;
                    temp++;
                    point++;
                    return couples[i].key;
                }
            }
            return null;
        }
    };
    @Override
    public Iterator<K> iterator() {
        return iterator;
    }

    private boolean contains(int index) {
        return index <= length && couples[index] != null;
    }

    private int hash(K key) {
        return (key.hashCode() % 100 + key.hashCode() % 10) % length;
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
