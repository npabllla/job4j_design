package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;

public class Meat extends Food {
    public Meat() {

    }
    public Meat(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
