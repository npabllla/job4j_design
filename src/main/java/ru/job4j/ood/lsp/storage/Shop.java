package ru.job4j.ood.lsp.storage;

public class Shop extends Storage {
    private double discount;

    public Shop(double discount) {
        this.discount = discount;
    }

    @Override
    boolean accept(Food food) {
        double percent = food.percentageOfDate();
        if (percent >= 75 && percent < 100) {
            food.setDiscount(discount);
        }
        return food.percentageOfDate() >= 25 && food.percentageOfDate() < 100;
    }
}
