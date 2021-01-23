package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int length = 0;

    public T poll() {
        if (in.isEmpty() && out.isEmpty()) {
            throw new NoSuchElementException();
        } else if (out.isEmpty()) {
            for (int i = 0; i < length; i++) {
                out.push(in.pop());
            }
            length--;
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        length++;
    }
}