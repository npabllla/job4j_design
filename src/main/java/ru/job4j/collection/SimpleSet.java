package ru.job4j.collection;

import java.util.*;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> array = new SimpleArray<>();
    private int size = 0;

    public boolean contains(E e) {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(e, array.get(i))) {
                    return true;
                }
            }
        return false;
    }

    public void add(E e) {
        if (!contains(e)) {
            array.add(e);
            size++;
        }
    }

    Iterator<E> iterator = new Iterator<>() {
        private int point = 0;
        @Override
        public boolean hasNext() {
            return point < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array.get(point++);
        }
    };

    @Override
    public Iterator<E> iterator() {
        return iterator;
    }
}
