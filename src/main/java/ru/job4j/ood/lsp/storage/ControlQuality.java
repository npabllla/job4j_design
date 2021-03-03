package ru.job4j.ood.lsp.storage;

public class ControlQuality {
    private Storage warehouse;
    private Storage shop;
    private Storage trash;

    public ControlQuality(Storage warehouse, Storage shop, Storage trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void control(Food food, double discount) {
        int percentageOfDate = food.percentageOfDate();
        if (percentageOfDate < 25) {
            warehouse.add(food);
        } else if (percentageOfDate > 25 && percentageOfDate <= 75) {
            shop.add(food);
        } else if (percentageOfDate > 75) {
            food.setDiscount(discount);
            shop.add(food);
        } else {
            trash.add(food);
        }
    }
}
