package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Car {
    private final int capacity;
    private final String brand;
    private final boolean presence;
    private final Engine engine;
    private final String[] colors;

    public int getCapacity() {
        return capacity;
    }

    public String getBrand() {
        return brand;
    }

    public boolean isPresence() {
        return presence;
    }

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
        JSONObject jsonEngine = new JSONObject("{\"power\":\"147.8\"}");
        List<String> colors = new ArrayList<>();
        colors.add("Black");
        colors.add("White");
        colors.add("Red");
        JSONArray jsonColors = new JSONArray(colors);
        JSONObject jsonCar = new JSONObject();
        jsonCar.put("capacity", car.getCapacity());
        jsonCar.put("brand", car.getBrand());
        jsonCar.put("presence", car.isPresence());
        jsonCar.put("engine", jsonEngine);
        jsonCar.put("colors", jsonColors);
        System.out.println(jsonCar.toString());
        System.out.println(new JSONObject((car)).toString());
    }
}
