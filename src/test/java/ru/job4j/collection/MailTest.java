package ru.job4j.collection;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.util.HashMap;

public class MailTest {
    @Test
    public void whenMerge() {
        HashMap<String, String[]> input = new HashMap<>();
        String[] user1 = new String[]{"someMail1", "someMail3"};
        String[] user2 = new String[]{"someMail3", "someMail4"};
        String[] user3 = new String[]{"someMail5", "someMail6"};
        input.put("User1", user1);
        input.put("User2", user2);
        input.put("User3", user3);
        HashMap<String, String[]> expected = new HashMap<>();
        String[] user2Ex = new String[]{"someMail3", "someMail4", "someMail1"};
        String[] user3Ex = new String[]{"someMail5", "someMail6"};
        expected.put("User2", user2Ex);
        expected.put("User3", user3Ex);
        input = Mail.merge(input);
        assertThat(input.get("User2"), is(expected.get("User2")));
        assertThat(input.get("User3"), is(expected.get("User3")));
    }

    @Test
    public void whenNotMerge() {
        HashMap<String, String[]> input = new HashMap<>();
        String[] user1 = new String[]{"someMail1", "someMail2"};
        String[] user2 = new String[]{"someMail3", "someMail4"};
        String[] user3 = new String[]{"someMail5", "someMail6"};
        input.put("User1", user1);
        input.put("User2", user2);
        input.put("User3", user3);
        HashMap<String, String[]> expected = new HashMap<>();
        String[] user1Ex = new String[]{"someMail1", "someMail2"};
        String[] user2Ex = new String[]{"someMail3", "someMail4"};
        String[] user3Ex = new String[]{"someMail5", "someMail6"};
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
        HashMap<String, String[]> input = new HashMap<>();
        String[] user1 = new String[]{"someMail1", "someMail2"};
        input.put("User1", user1);
        HashMap<String, String[]> expected = new HashMap<>();
        String[] user1Ex = new String[]{"someMail1", "someMail2"};
        expected.put("User1", user1Ex);
        input = Mail.merge(input);
        assertThat(input.get("User1"), is(expected.get("User1")));
    }

    @Test
    public void whenOnlyZeroUsers() {
        HashMap<String, String[]> input = new HashMap<>();
        HashMap<String, String[]> expected = new HashMap<>();
        input = Mail.merge(input);
        assertThat(input, is(expected));
    }
}