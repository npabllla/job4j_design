package ru.job4j.template;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SolutionTest {
    @Test
    public void whenAllRight() {
        Solution solution = new Solution();
        assertThat(solution.produce("I am a ${name}, Who are ${subject}?", Map.of("name", "Petr", "subject", "you")), is("I am a Petr, Who are you?"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotEnoughKeys() {
        Solution solution = new Solution();
        solution.produce("I am a ${name}, Who are ${subject}? ${emotion}", Map.of("name", "Petr", "subject", "you"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenToManyKeys() {
        Solution solution = new Solution();
        solution.produce("I am a ${name}, Who are ${subject}?", Map.of("name", "Petr", "subject", "you", "emotion", "Nice to meet u"));
    }
}