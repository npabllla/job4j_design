package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxMinTest {
    @Test
    public void whenMax() {
        MaxMin max = new MaxMin();
        assertThat(max.max(List.of(4, 3, 5, 1, -1, 111), Comparator.naturalOrder()), is(111));
    }

    @Test
    public void whenMin() {
        MaxMin max = new MaxMin();
        assertThat(max.min(List.of(4, 3, 5, 1, -1, 111), Comparator.naturalOrder()), is(-1));
    }
}