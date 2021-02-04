package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Find {
    public void writer(List<Path> sources, File target) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(target))) {
            for (Path src : sources) {
                wr.write(src.toString() + System.lineSeparator());
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
            Path directory = Paths.get(argFind.directory());
            String name = argFind.name();
            String type = argFind.type();
            File output = new File(argFind.output());
            List<Path> sources = search(directory, name, type);
            new Find().writer(sources, output);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
