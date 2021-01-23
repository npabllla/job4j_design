package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<K> {
    private int length = 16;
    private int size = 0;
    private Object[] keyContainer = new Object[length];
    private Object[] valueContainer = new Object[length];

    private boolean contains(K key) {
        for (int i = 0; i < size; i++) {
            if (keyContainer[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean insert(K key, V value) {
        if (contains(key)) {
            return false;
        }
        if (size == length) {
            length *= 2;
            keyContainer = Arrays.copyOf(keyContainer, length);
            valueContainer = Arrays.copyOf(valueContainer, length);
        }
        keyContainer[size] = key;
        valueContainer[size] = value;
        size++;
        return true;
    }

    public V get(K key) {
        if (contains(key)) {
            for (int i = 0; i < size; i++) {
                if (keyContainer[i].equals(key)) {
                    return (V) valueContainer[i];
                }
            }
        } else {
            throw new NullPointerException();
        }
        return null;
    }

    public boolean delete(K key) {
        if (contains(key)) {
            for (int i = 0; i < size; i++) {
                if (keyContainer[i].equals(key)) {
                    keyContainer[i] = null;
                    valueContainer[i] = null;
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
            return (K) keyContainer[point++];
        }
    };
    @Override
    public Iterator<K> iterator() {
        return iterator;
    }
}
