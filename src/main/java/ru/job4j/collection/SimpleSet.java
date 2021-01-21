package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> array = new SimpleArray<>();
    private int size = 0;

    public void add(E e) {
        int repeats = 0;
        for (int i = 0; i < size; i++) {
            if (e.equals(array.get(i))) {
                repeats++;
            }
        }
        if (repeats == 0) {
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
