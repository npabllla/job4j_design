package ru.job4j.io;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class AnalizyTest {
    @Test
    public void whenTwoPeriod() {
        Analizy analizy = new Analizy();
        List<String> expected = new ArrayList<>();
        expected.add("10:57:01;10:59:01");
        expected.add("11:01:02;11:02:02");
        analizy.unavailable("serverLog.txt", "unavailable.csv");
        assertThat(analizy.getResult(), is(expected));
    }

    @Test
    public void whenOnePeriod() {
        Analizy analizy = new Analizy();
        List<String> expected = new ArrayList<>();
        expected.add("10:57:01;11:02:02");
        analizy.unavailable("serverLogFirstTest.txt", "unavailable.csv");
        assertThat(analizy.getResult(), is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongLogInfo() {
        Analizy analizy = new Analizy();
        analizy.unavailable("ServerLogWrongInput.txt" , "unavailable.csv");
    }
}