package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<K> {
    static final float LOAD_FACTOR = 0.75f;
    private int length = 16;
    private int size = 0;
    private int modCount = 0;
    private Node<K, V>[] couples = new Node[length];

    public boolean insert(K key, V value) {
        int index = hash(key);
        if (contains(index)) {
            return false;
        }
        if (size >= LOAD_FACTOR * length) {
            length *= 2;
            couples = resize(length);
        }
        couples[index] = new Node<>(key, value);
        size++;
        modCount++;
        return true;
    }

    public V get(K key) {
        int index = hash(key);
        if (contains(index) && couples[index].key.equals(key)) {
            return couples[index].value;
        }
        return null;
    }

    public boolean delete(K key) {
        int index = hash(key);
        if (contains(index) && couples[index].key.equals(key)) {
           couples[index] = null;
           size--;
           return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int point = 0;
            private int temp = 0;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
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
    }

    private Node<K, V>[] resize(int length) {
        Node<K, V>[] oldTable = couples;
        Node<K, V>[] newTable = new Node[length];
        couples = newTable;
        for (Node<K, V> kvNode : oldTable) {
            Node<K, V> e;
            if (kvNode != null) {
                e = kvNode;
                newTable[hash(e.key)] = e;
            }
        }
        return newTable;
    }

    private boolean contains(int index) {
        return couples[index] != null;
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
