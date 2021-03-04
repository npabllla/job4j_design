package ru.job4j.ood.lsp.parking;

public abstract class ParkingSpace {
    private int size;
    private int position;
    private Car car;

    public ParkingSpace(int size, int position) {
        this.size = size;
        this.position = position;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isFree(Car car) {
        return false;
    }
}
