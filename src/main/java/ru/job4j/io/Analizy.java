package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analizy {
    private String[] lines;
    private String line;
    private List<String> result = new ArrayList<>();

    public void unavailable(String source, String target) throws IllegalArgumentException {
        String dropStart = null;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            while ((line = read.readLine()) != null) {
                lines = line.split(" ");
                if (lines.length < 2) {
                    throw new IllegalArgumentException();
                } else if ((Integer.parseInt(lines[0]) == 400 || Integer.parseInt(lines[0]) == 500)
                        && dropStart == null) {
                    dropStart = lines[1];
                }
                if (!(Integer.parseInt(lines[0]) == 400) && !(Integer.parseInt(lines[0]) == 500)
                        && dropStart != null) {
                    StringBuilder sb = new StringBuilder();
                    result.add(sb.append(dropStart).append(";").append(lines[1]).toString());
                    dropStart = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        write(result, target);
    }

    private static void write(List<String> result, String target) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String rsl : result) {
                out.write(rsl);
                out.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}