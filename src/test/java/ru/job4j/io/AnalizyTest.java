package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenTwoPeriod() throws IOException, IllegalArgumentException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01" + "11:01:02;11:02:02"));
    }

    @Test
    public void whenOnePeriod() throws IOException, IllegalArgumentException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;11:02:02"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongLogInfo() throws IOException, IllegalArgumentException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 ");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
    }
}