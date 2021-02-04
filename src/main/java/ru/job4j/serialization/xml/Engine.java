package ru.job4j.serialization.xml;

public class Engine {
    private double power;

    public Engine(double power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "power= " + power + '\''
                + '}';
    }
}
