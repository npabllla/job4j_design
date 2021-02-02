package ru.job4j.collection;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MailTest {
    @Test
    public void whenMerge() {
        Map<String, Set<String>> input = new HashMap<>();
        Set<String> user1 = new HashSet<>();
        Set<String> user2 = new HashSet<>();
        Set<String> user3 = new HashSet<>();
        input.put("User1", user1);
        input.put("User2", user2);
        input.put("User3", user3);
        Map<String, Set<String>> expected = new HashMap<>();
        Set<String> user2Ex = new HashSet<>();
        Set<String> user3Ex = new HashSet<>();
        expected.put("User2", user2Ex);
        expected.put("User3", user3Ex);
        input = Mail.merge(input);
        assertThat(input.get("User2"), is(expected.get("User2")));
        assertThat(input.get("User3"), is(expected.get("User3")));
    }

    @Test
    public void whenNotMerge() {
        Map<String, Set<String>> input = new HashMap<>();
        Set<String> user1 = new HashSet<>();
        Set<String> user2 = new HashSet<>();
        Set<String> user3 = new HashSet<>();
        input.put("User1", user1);
        input.put("User2", user2);
        input.put("User3", user3);
        Map<String, Set<String>> expected = new HashMap<>();
        Set<String> user1Ex = new HashSet<>();
        Set<String> user2Ex = new HashSet<>();
        Set<String> user3Ex = new HashSet<>();
        expected.put("User1", user1Ex);
        expected.put("User2", user2Ex);
        expected.put("User3", user3Ex);
        input = Mail.merge(input);
        assertThat(input.get("User1"), is(expected.get("User1")));
        assertThat(input.get("User2"), is(expected.get("User2")));
        assertThat(input.get("User3"), is(expected.get("User3")));
    }

    @Test
    public void whenOnlyOneUser() {
        Map<String, Set<String>> input = new HashMap<>();
        Set<String> user1 = new HashSet<>();
        input.put("User1", user1);
        HashMap<String, Set<String>> expected = new HashMap<>();
        Set<String> user1Ex = new HashSet<>();
        expected.put("User1", user1Ex);
        input = Mail.merge(input);
        assertThat(input.get("User1"), is(expected.get("User1")));
    }

    @Test
    public void whenOnlyZeroUsers() {
        Map<String, Set<String>> input = new HashMap<>();
        Map<String, Set<String>> expected = new HashMap<>();
        input = Mail.merge(input);
        assertThat(input, is(expected));
    }
}