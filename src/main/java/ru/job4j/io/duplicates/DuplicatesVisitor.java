package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<FileProperty> result = new ArrayList<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        result.add(new FileProperty(file.toFile().getTotalSpace(), file.toFile().getName()));
        Set<FileProperty> tmp = new HashSet<>(result);
        result.removeIf(tmp::add);
        System.out.println(result);
        return super.visitFile(file, attrs);
    }
}