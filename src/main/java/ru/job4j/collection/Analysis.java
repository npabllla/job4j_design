package ru.job4j.collection;

import java.util.*;

public class Analysis {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, User> prevMap = new HashMap<>();
        for (User user : previous) {
            prevMap.put(user.id, user);
        }
        for (User user : current) {
            if (prevMap.get(user.id) == null) {
                prevMap.remove(user.id);
                info.added++;
            } else if (!prevMap.get(user.id).equals(user)) {
                prevMap.remove(user.id);
                info.changed++;
            } else {
                prevMap.remove(user.id);
            }
        }
        info.deleted = prevMap.size();
        return info;
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
