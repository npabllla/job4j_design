package ru.job4j.gc.cash;


import java.io.File;
import java.util.Objects;

public class SimpleCash {
    private Cash<String, String> cash = new Cash<>();

    public boolean fillData(String directory) {
        File file = new File(directory);
        if (!file.isDirectory()) {
            return false;
        }
        for (File fl : Objects.requireNonNull(file.listFiles())) {
            cash.add(fl.getAbsolutePath());
        }
        return true;
    }

    public String getData(String path) {
        return cash.get(path);
    }
}
