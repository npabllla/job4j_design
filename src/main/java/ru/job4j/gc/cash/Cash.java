package ru.job4j.gc.cash;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cash<K, V> {
    private Map<K, SoftReference<V>> cash = new HashMap<>();

    public void add(K key) {
        cash.put(key, new SoftReference<>(fileInfo(key)));
    }

    public V get(K key) {
        if (cash.containsKey(key)) {
            SoftReference<V> value = cash.get(key);
            if (value == null) {
                add(key);
                return get(key);
            } else {
                return value.get();
            }
        } else {
            add(key);
            return cash.get(key).get();
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
