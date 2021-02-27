package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return search(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return search(value, comparator.reversed());
    }

    private <T> T search(List<T> value, Comparator<T> comparator) {
        T result = null;
        for (int i = 0; i < value.size(); i++) {
            for (T t : value) {
                if (comparator.compare(value.get(i), t) > 0) {
                    result = value.get(i);
                    break;
                }
            }
        }
        return result;
    }
}