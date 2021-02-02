package ru.job4j.io;

import java.util.*;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        List<String[]> list = new ArrayList<>();
        for (String ar : args) {
            list.add(ar.split("="));
        }
        if (list.size() < 2) {
            throw new IllegalArgumentException();
        } else {
            for (String[] l : list) {
                if (l.length < 2) {
                    throw new IllegalArgumentException();
                } else {
                    values.put(l[0].substring(1), l[1]);
                }
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}