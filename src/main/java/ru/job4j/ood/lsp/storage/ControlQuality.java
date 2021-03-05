package ru.job4j.ood.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void control(Food food) {
        distribution(storages, food);
    }

    public void resort(List<Storage> storages) {
        List<Food> products = new ArrayList<>();
        storages.forEach(e -> {
            products.addAll(e.findAll());
            e.deleteAll(e.findAll());
        });
        for (Food food : products) {
            distribution(storages, food);
        }
    }

    private void distribution(List<Storage> storages, Food food) {
        for (Storage storage : storages) {
            if (storage.accept(food)) {
                storage.add(food);
                break;
            }
        }
    }
}
