package ru.job4j.collection;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.util.ArrayList;
import java.util.List;

public class AnalysisTest {
    @Test
    public void whenAdded() {
        Analysis analysis = new Analysis();
        List<Analysis.User> previous = new ArrayList<>();
        List<Analysis.User> current = new ArrayList<>();
        Analysis.Info info;
        previous.add(new Analysis.User(1, "A"));
        previous.add(new Analysis.User(2, "B"));
        previous.add(new Analysis.User(3, "C"));
        previous.add(new Analysis.User(4, "D"));
        previous.add(new Analysis.User(5, "E"));
        previous.add(new Analysis.User(6, "F"));
        current.add(new Analysis.User(1, "A"));
        current.add(new Analysis.User(2, "B"));
        current.add(new Analysis.User(3, "C"));
        current.add(new Analysis.User(4, "D"));
        current.add(new Analysis.User(5, "E"));
        current.add(new Analysis.User(6, "F"));
        current.add(new Analysis.User(7, "G"));
        info = analysis.diff(previous, current);
        assertThat(info.added, is(1));
    }

    @Test
    public void whenDeleted() {
        Analysis analysis = new Analysis();
        List<Analysis.User> previous = new ArrayList<>();
        List<Analysis.User> current = new ArrayList<>();
        Analysis.Info info;
        previous.add(new Analysis.User(1, "A"));
        previous.add(new Analysis.User(2, "B"));
        previous.add(new Analysis.User(3, "C"));
        previous.add(new Analysis.User(4, "D"));
        previous.add(new Analysis.User(5, "E"));
        previous.add(new Analysis.User(6, "F"));
        current.add(new Analysis.User(1, "A"));
        current.add(new Analysis.User(2, "B"));
        current.add(new Analysis.User(3, "C"));
        current.add(new Analysis.User(4, "D"));
        current.add(new Analysis.User(5, "E"));
        info = analysis.diff(previous, current);
        assertThat(info.deleted, is(1));
    }

    @Test
    public void whenChanged() {
        Analysis analysis = new Analysis();
        List<Analysis.User> previous = new ArrayList<>();
        List<Analysis.User> current = new ArrayList<>();
        Analysis.Info info;
        previous.add(new Analysis.User(1, "A"));
        previous.add(new Analysis.User(2, "B"));
        previous.add(new Analysis.User(3, "C"));
        previous.add(new Analysis.User(4, "D"));
        previous.add(new Analysis.User(5, "E"));
        previous.add(new Analysis.User(6, "F"));
        current.add(new Analysis.User(1, "A"));
        current.add(new Analysis.User(2, "Ba"));
        current.add(new Analysis.User(3, "C"));
        current.add(new Analysis.User(4, "Da"));
        current.add(new Analysis.User(5, "E"));
        current.add(new Analysis.User(6, "Fa"));
        info = analysis.diff(previous, current);
        assertThat(info.changed, is(3));
    }

    @Test
    public void whenChangedAndAdded() {
        Analysis analysis = new Analysis();
        List<Analysis.User> previous = new ArrayList<>();
        List<Analysis.User> current = new ArrayList<>();
        Analysis.Info info;
        previous.add(new Analysis.User(1, "A"));
        previous.add(new Analysis.User(2, "B"));
        previous.add(new Analysis.User(3, "C"));
        previous.add(new Analysis.User(4, "D"));
        previous.add(new Analysis.User(5, "E"));
        previous.add(new Analysis.User(6, "F"));
        current.add(new Analysis.User(1, "A"));
        current.add(new Analysis.User(2, "Ba"));
        current.add(new Analysis.User(3, "C"));
        current.add(new Analysis.User(4, "Da"));
        current.add(new Analysis.User(5, "E"));
        current.add(new Analysis.User(6, "Fa"));
        current.add(new Analysis.User(7, "G"));
        current.add(new Analysis.User(8, "H"));
        info = analysis.diff(previous, current);
        assertThat(info.changed, is(3));
        assertThat(info.added, is(2));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void whenChangedAndDeleted() {
        Analysis analysis = new Analysis();
        List<Analysis.User> previous = new ArrayList<>();
        List<Analysis.User> current = new ArrayList<>();
        Analysis.Info info;
        previous.add(new Analysis.User(1, "A"));
        previous.add(new Analysis.User(2, "B"));
        previous.add(new Analysis.User(3, "C"));
        previous.add(new Analysis.User(4, "D"));
        previous.add(new Analysis.User(5, "E"));
        previous.add(new Analysis.User(6, "F"));
        current.add(new Analysis.User(1, "A"));
        current.add(new Analysis.User(2, "Ba"));
        current.add(new Analysis.User(3, "C"));
        current.add(new Analysis.User(4, "Da"));
        current.add(new Analysis.User(5, "E"));
        info = analysis.diff(previous, current);
        assertThat(info.changed, is(2));
        assertThat(info.deleted, is(1));
        assertThat(info.added, is(0));
    }

    @Test
    public void whenChangedAndDeletedAndAdded() {
        Analysis analysis = new Analysis();
        List<Analysis.User> previous = new ArrayList<>();
        List<Analysis.User> current = new ArrayList<>();
        Analysis.Info info;
        previous.add(new Analysis.User(1, "A"));
        previous.add(new Analysis.User(2, "B"));
        previous.add(new Analysis.User(3, "C"));
        previous.add(new Analysis.User(4, "D"));
        previous.add(new Analysis.User(5, "E"));
        previous.add(new Analysis.User(6, "F"));
        current.add(new Analysis.User(1, "A"));
        current.add(new Analysis.User(2, "Ba"));
        current.add(new Analysis.User(3, "C"));
        current.add(new Analysis.User(4, "Da"));
        current.add(new Analysis.User(5, "E"));
        current.add(new Analysis.User(7, "Q"));
        current.add(new Analysis.User(8, "R"));
        info = analysis.diff(previous, current);
        assertThat(info.changed, is(2));
        assertThat(info.deleted, is(1));
        assertThat(info.added, is(2));
    }
}