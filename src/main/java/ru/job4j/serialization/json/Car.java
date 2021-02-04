package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final int capacity;
    private final String brand;
    private final boolean presence;
    private final Engine engine;
    private final String[] colors;

    public Car(int capacity, String brand, boolean presence, Engine engine, String[] colors) {
        this.capacity = capacity;
        this.brand = brand;
        this.presence = presence;
        this.engine = engine;
        this.colors = colors;
    }
    

    @Override
    public String toString() {
        return "Car{" 
                + "capacity= " + capacity 
                + ", model=' " + brand + '\''
                + ", presence= " + presence 
                + ", engine= " + engine 
                + ", colors= " + Arrays.toString(colors) 
                + '}';
    }

    public static void main(String[] args) {
        final  Car car = new Car(5, "someBrand1", true, new Engine(147.8), new String[]{"Black", "White", "Red"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
        final String carJson =
                "{"
                        + "\"capacity\": 7,"
                        + "\"brand\":someBrand2,"
                        + "\"presence\":true,"
                        + "\"engine\":"
                        + "{"
                        + "\"power\": 172"
                        + "},"
                        + "\"colors\":"
                        + "[\"Blue\",\"Yellow\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
