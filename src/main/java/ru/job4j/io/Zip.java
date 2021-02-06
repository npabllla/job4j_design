package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path src : sources) {
                zip.putNextEntry(new ZipEntry(src.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(src.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
               e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Path> getSources(Path path, String exclude) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(exclude));
        Files.walkFileTree(path, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        if (!argZip.valid()) {
            throw new IllegalArgumentException();
        }
        Path directory = Paths.get(argZip.directory());
        File output = new File(argZip.output());
        List<Path> source = new ArrayList<>(getSources(directory, argZip.exclude()));
        new Zip().packFiles(source, output);
    }
}