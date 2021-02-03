package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        Map<String, Integer> logs = new LinkedHashMap<>();
        List<String> result = new ArrayList<>();
        String dropStart = null;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            List<String> list = read.lines().collect(Collectors.toList());
            for (String st : list) {
                String[] tmp = st.split(" ");
                if (tmp.length < 2) {
                    throw new IllegalArgumentException();
                } else {
                    logs.put(tmp[1], Integer.parseInt(tmp[0]));
                }
            }
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
                out.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}