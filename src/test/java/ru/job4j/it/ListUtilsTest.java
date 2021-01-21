package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(List.of(1, 3, 4, 5, 6));
        ListUtils.addAfter(input,3,2);
        assertThat(input, Is.is(List.of(1, 3, 4, 5, 2, 6)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenRemoveIf(){
        List<Integer> input = new ArrayList<>(List.of(0,1,2,3,4,5,6));
        List<Integer> expected = ListUtils.removeIf(input, i -> i > 3);
        assertThat(input, Is.is(expected));
    }

    @Test
    public void whenReplaceIf(){
        List<Integer> input = new ArrayList<>(List.of(0,1,2,3,4,5,6));
        List<Integer> expected = ListUtils.replaceIf(input,i -> i % 2 == 0, 0);
        assertThat(input, Is.is(expected));
    }

    @Test
    public void whenReplaceAll(){
        List<Integer> input = new ArrayList<>(List.of(0,1,0,3,0,5,0));
        List<Integer> elements = new ArrayList<>(List.of(0));
        List<Integer> expected = ListUtils.removeAll(input,elements);
        assertThat(input, Is.is(expected));
    }
}