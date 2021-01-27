package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleHashMapTest {

    @Test
    public void whenInsert() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        List<String> list = new ArrayList<>();
        assertThat(map.insert("One", 1), is(true));
        assertThat(map.insert("One", 1), is(false));
        assertThat(map.insert("Four", 2), is(true));
        assertThat(map.insert("Three", 3), is(true));
        assertThat(map.insert("Two", 4), is(true));
        assertThat(map.insert("Two", 4), is(false));
        for (String s : map) {
            list.add(s);
        }
        assertThat(list, is(List.of("Three", "Four", "Two", "One")));
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

    @Test
    public void whenIncorrectGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.insert("Three", 3);
        map.insert("Four", 4);
        assertNull(map.get("Zero"));
    }

    @Test
    public void whenDelete() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("One", 1);
        map.insert("Two", 2);
        map.insert("Three", 3);
        map.insert("Four", 4);
        assertThat(map.delete("Three"), is(true));
        assertNull(map.get("Three"));
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