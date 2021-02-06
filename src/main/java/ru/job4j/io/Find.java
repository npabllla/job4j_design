package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Find {
    public void writer(List<Path> sources, File target) {
        try (PrintWriter wr = new PrintWriter(new FileWriter(target))) {
            for (Path src : sources) {
                wr.write(src.toString());
                wr.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Path> search(Path path, String value, String type) throws IOException {
        SearchFiles searcher;
        if (type.equals("mask")) {
            searcher = new SearchFiles(p -> p.toFile().getName().endsWith(value));
        } else if (type.equals("name")) {
            searcher = new SearchFiles(p -> p.toFile().getName().equals(value));
        } else {
            throw new IllegalArgumentException();
        }
        Files.walkFileTree(path, searcher);
        return searcher.getPaths();
    }


    public static void main(String[] args) throws IOException {
        ArgFind argFind = new ArgFind(args);
        if (argFind.valid()) {
            List<Path> sources = search(Paths.get(argFind.directory()), argFind.name(), argFind.type());
            new Find().writer(sources, new File(argFind.output()));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
