package ru.job4j.ood.lsp.storage;

public class Warehouse extends Storage {

    @Override
    boolean accept(Food food) {
        return food.percentageOfDate() < 25;
    }
}
