package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public interface Parking {
    List<Car> CARS = new ArrayList<>();

    boolean addCar();
    boolean deleteCar();
}
