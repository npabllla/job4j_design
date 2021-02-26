package ru.job4j.gc.cash;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cash<K, V> {
    private Map<K, SoftReference<V>> cash = new HashMap<>();

    public V add(K key) {
        V value = fileInfo(key);
        cash.put(key, new SoftReference<>(value));
        return value;
    }

    public V get(K key) {
        if (cash.containsKey(key)) {
            V text = cash.get(key).get();
            if (text == null) {
                return add(key);
            } else {
                return text;
            }
        } else {
            return add(key);
        }
    }

    private V fileInfo(K path) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader((String) path))) {
            bf.lines().forEach(e -> sb.append(e).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (V) sb.toString();
    }
}
