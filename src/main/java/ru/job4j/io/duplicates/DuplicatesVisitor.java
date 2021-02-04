package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<FileProperty> temp = new ArrayList<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        temp.add(new FileProperty(file.toFile().getTotalSpace(), file.toFile().getName()));
        List<FileProperty> result = new ArrayList<>();
        Set<FileProperty> tmp = new HashSet<>();
        for (FileProperty fp : temp) {
            if (tmp.contains(fp)) {
                result.add(fp);
            } else {
                tmp.add(fp);
            }
        }
        System.out.println(result);
        return super.visitFile(file, attrs);
    }
}