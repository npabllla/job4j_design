package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        Map<String, Integer> logs;
        List<String> result = new ArrayList<>();
        String dropStart = null;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            logs = read.lines()
                    .map(e -> e.split(" "))
                    .filter(e -> e.length == 2)
                    .collect(Collectors.toMap(e -> e[1], e -> Integer.parseInt(e[0]), (e1, e2) -> e1, LinkedHashMap::new));
            for (String lg : logs.keySet()) {
                if ((logs.get(lg).equals(400) || logs.get(lg).equals(500)) && dropStart == null) {
                    dropStart = lg;
                }
                if (!logs.get(lg).equals(400) && !logs.get(lg).equals(500) && dropStart != null) {
                    StringBuilder sb = new StringBuilder();
                    result.add(sb.append(dropStart).append(";").append(lg).toString());
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