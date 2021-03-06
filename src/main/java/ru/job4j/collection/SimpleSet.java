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

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}
