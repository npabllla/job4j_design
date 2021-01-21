package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleSetTest {
    @Test
    public void whenAdd(){
        SimpleSet<Integer> input = new SimpleSet<>();
        List<Integer> expected = new ArrayList<>(List.of(1,2,3,4,5));
        List<Integer> numbers = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        for (int i = 0; i < 5; i++) {
            numbers.add(input.iterator().next());
        }
        assertThat(numbers, is(expected));
    }

    @Test
    public void whenAddSame(){
        SimpleSet<Integer> input = new SimpleSet<>();
        List<Integer> expected = new ArrayList<>(List.of(1,2,3));
        List<Integer> numbers = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(1);
        input.add(2);
        input.add(3);
        for (int i = 0; i < 3; i++) {
            numbers.add(input.iterator().next());
        }
        assertThat(numbers, is(expected));
    }
}