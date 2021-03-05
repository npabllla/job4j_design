package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Storage {
    private List<Food> storage = new ArrayList<>();

    boolean add(Food food) {
        return storage.add(food);
    }

    public boolean deleteAll(List<Food> products) {
        return storage.removeAll(products);
    }

    boolean delete(Food food) {
        return storage.remove(food);
    }

    List<Food> findAll() {
        return storage;
    }

    List<Food> findByName(String name) {
        return storage.stream()
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
    }

    abstract boolean accept(Food food);
}
