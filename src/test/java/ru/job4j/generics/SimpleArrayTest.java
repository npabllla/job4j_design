package ru.job4j.generics;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class SimpleArrayTest {
    @Test
    public void whenAdd() {
        SimpleArray<Integer> numbers = new SimpleArray<>(5);
        Integer[] expected = new Integer[]{0,1,2,3,4};
        Integer[] input = new Integer[numbers.getLength()];
        for (int i = 0; i < numbers.getLength(); i++) {
            numbers.add(i);
        }
        for (int i = 0; i < input.length; i++) {
            input[i] = numbers.iterator().next();
        }
        assertThat(input, is(expected));
    }

    @Test
    public void whenSet(){
        SimpleArray<Integer> numbers = new SimpleArray<>(5);
        Integer[] expected = new Integer[]{9,1,2,3,4};
        Integer[] input = new Integer[numbers.getLength()];
        for (int i = 0; i < numbers.getLength(); i++) {
            numbers.add(i);
        }
        numbers.set(0,9);
        for (int i = 0; i < input.length; i++) {
            input[i] = numbers.iterator().next();
        }
        assertThat(input, is(expected));
    }

    @Test
    public void whenGet() {
        SimpleArray<Integer> numbers = new SimpleArray<>(5);
        for (int i = 0; i < numbers.getLength(); i++) {
            numbers.add(i);
        }
        assertThat(numbers.get(3),is(3));
    }

    @Test
    public void whenRemove() {
        SimpleArray<Integer> numbers = new SimpleArray<>(5);
        Integer[] expected = new Integer[]{1,2,3,4};
        for (int i = 0; i < numbers.getLength(); i++) {
            numbers.add(i);
        }
        numbers.remove(0);
        Integer[] input = new Integer[numbers.getLength()];
        for (int i = 0; i < input.length; i++) {
            input[i] = numbers.iterator().next();
        }
        assertThat(input, is(expected));
    }
}