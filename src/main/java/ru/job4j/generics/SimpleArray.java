package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T>{
    private int size;
    private T[] array;

    public SimpleArray(int size) {
        this.size = size;
        array = (T[])new Object[this.size];
    }

    public int getSize() {
        return size;
    }

    public void add(T model){
        for (int i = 0; i < size; i++) {
            if (array[i] == null){
                array[i] = model;
                break;
            }
        }
    }

    public T[] set(int index, T model){
        array[Objects.checkIndex(index, size)] = model;
        return array;
    }

    public T[] remove(int index){
        int chekIndex = Objects.checkIndex(index,size);
        System.arraycopy(array, chekIndex + 1, array, chekIndex, array.length - chekIndex - 1);
        size--;
        return array;
    }

    public T get(int index){
        return array[Objects.checkIndex(index,size)];
    }


    Iterator<T> iterator = new  Iterator<>() {
    private int point = 0;
        @Override
        public boolean hasNext() {
            return point < size;
        }

        @Override
        public T next() {
            return array[point++];
        }
    };
    @Override
    public Iterator<T> iterator() {
        return iterator;
    }
}
