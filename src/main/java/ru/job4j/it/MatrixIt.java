package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (row >= data.length) {
            return false;
        }
        int k = 0;
        for (int i = row; i < data.length; i++) {
            if (data[i].length == 0) {
                k++;
            }
            if(k==data.length) {
                return false;
            }
        }
        return column < data[row].length || row != data.length - 1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while(column >= data[row].length){
            row++;
            column = 0;
        }
        return data[row][column++];
    }
}