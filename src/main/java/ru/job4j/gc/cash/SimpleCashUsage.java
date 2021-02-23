package ru.job4j.gc.cash;

import java.io.File;

public class SimpleCashUsage {
    public static void main(String[] args) {
        SimpleCash cash = new SimpleCash();
        cash.fillData("./src/data");
        File[] fileList = new File("./src/data").listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                System.out.println(cash.getData(file.getAbsolutePath()));
            }
        }
    }
}
