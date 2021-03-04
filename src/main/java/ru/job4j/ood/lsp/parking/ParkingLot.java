package ru.job4j.ood.lsp.parking;

import java.util.List;

public class ParkingLot implements Parking {
    private List<Car> cars;
    private List<ParkingSpace> spaces;

    public ParkingLot(List<Car> cars, List<ParkingSpace> spaces) {
        this.cars = cars;
        this.spaces = spaces;
    }

    @Override
    public boolean addCar(Car car) {
        return false;
    }

    @Override
    public boolean deleteCar(Car car) {
        return false;
    }
}
