package ru.job4j.collection;

import java.util.*;

public class Analysis {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info(0, 0, 0);
        List<User> both = new ArrayList<>(previous);
        both.addAll(current);
        Map<User, Integer> map = new HashMap<>();
        for (User user : both) {
            if (map.containsKey(user)) {
                map.put(user, map.get(user) + 1);
            } else {
                map.put(user, 1);
            }
        }
        Map<Integer, Integer> userMap = new HashMap<>();
        for (User user : map.keySet()) {
            if (map.get(user) < 2 && userMap.containsKey(user.id)) {
                userMap.put(user.id, userMap.get(user.id) + 1);
            } else if (map.get(user) < 2) {
                userMap.put(user.id, 1);
            }
        }
        info.changed = (int) userMap.entrySet().stream()
                .filter(m -> m.getValue() >= 2)
                .count();
        info.added = find(userMap, current);
        info.deleted = find(userMap, previous);
        return info;
    }

    private int find(Map<Integer, Integer> map, List<User> list) {
        return (int) map.entrySet().stream()
                .filter(m -> m.getValue() == 1 && list.contains(findById(list, m.getKey())))
                .count();
    }

    private User findById(List<User> users, int id) {
        for (User user : users) {
            if (user.id == id) {
                return user;
            }
        }
        return null;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }
}
