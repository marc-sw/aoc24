package com.marc.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputSupplier {

    public static String supply(int day, boolean test) {
        String dir = test ? "test": "main";
        Path path = Paths.get("resources", dir, String.format("%02d.txt", day));
        if (!path.toFile().exists()) {
            throw new RuntimeException("no %s data file found for day %d, check if the file exists".formatted(dir, day));
        }
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
