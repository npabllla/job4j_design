package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<FileProperty> rsl = new ArrayList<>();
    private Map<FileProperty, Integer> temp = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        rsl.add(new FileProperty(file.toFile().getTotalSpace(), file.toFile().getName()));
        for (FileProperty fp : rsl) {
            if (!temp.containsKey(fp)) {
                temp.put(fp, 1);
            } else {
                temp.put(fp, temp.get(fp) + 1);
            }
        }
        List<FileProperty> result = new ArrayList<>();
        for (FileProperty fp : temp.keySet()) {
            if (temp.get(fp) >= 2) {
                result.add(fp);
            }
        }
        System.out.println(result);
        return super.visitFile(file, attrs);
    }
}