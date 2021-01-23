package ru.job4j.collection;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleHashMapTest {

    @Test
    public void whenInsert() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        assertThat(map.insert("One", 1), is(true));
        assertThat(map.insert("One", 1), is(false));
        assertThat(map.insert("Four", 2), is(true));
        assertThat(map.insert("Three", 3), is(true));
        assertThat(map.insert("Two", 4), is(true));
        assertThat(map.insert("Two", 4), is(false));
        assertThat(map.iterator().next(), is("One"));
        assertThat(map.iterator().next(), is("Four"));
        assertThat(map.iterator().next(), is("Three"));
        assertThat(map.iterator().next(), is("Two"));
    }

    @Test
    public void whenGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.insert("Three", 3);
        map.insert("Four", 4);
        assertThat(map.get("Three"), is(3));
    }

    @Test(expected = NullPointerException.class)
    public void whenIncorrectGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.insert("Three", 3);
        map.insert("Four", 4);
        map.get("Zero");
    }

    @Test(expected = NullPointerException.class)
    public void whenDelete() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.insert("Three", 3);
        map.insert("Four", 4);
        assertThat(map.delete("Three"), is(true));
        map.get("Three");
    }

    @Test
    public void whenIncorrectDelete() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.insert("Three", 3);
        map.insert("Four", 4);
        assertThat(map.delete("Five"), is(false));
    }
}