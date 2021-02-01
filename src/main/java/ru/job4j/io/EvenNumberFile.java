package ru.job4j.io;

import java.io.*;

public class EvenNumberFile {
    public static void main(String[] args) throws IOException {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(Integer.parseInt(line) + " is even");
                } else {
                    System.out.println(Integer.parseInt(line) + " is not even");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
