package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T>{
    private int size;
    private int point = 0;
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
        T[] rsl = (T[])new Object[size-1];
        System.arraycopy(array, 0, rsl, 0, chekIndex);
        System.arraycopy(array, chekIndex + 1, rsl, chekIndex, array.length - chekIndex - 1);
        size--;
        array = rsl;
        return array;
    }

    public T get(int index){
        return array[Objects.checkIndex(index,size)];
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                return array[point++];
            }
        };
    }
}
