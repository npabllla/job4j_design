package ru.job4j.map;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Calendar date = new GregorianCalendar(2000, Calendar.MARCH, 16);
        User user1 = new User("Ivan", 2, date);
        User user2 = new User("Ivan", 2, date);
        Map<User, Object> map = new HashMap<>();
        Object o1 = new Object();
        map.put(user1, o1);
        map.put(user2, null);
        System.out.println(map);
    }
}
