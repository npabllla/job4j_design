package ru.job4j.ood.lsp.storage;

public class Trash extends Storage {

    @Override
    boolean accept(Food food) {
        return food.percentageOfDate() >= 100;
    }
}
