package com.marc.aoc.deserializer;

import java.util.ArrayList;
import java.util.List;

public class LinesToListListLong implements Deserializer<List<List<Long>>> {

    @Override
    public List<List<Long>> deserialize(String puzzleInput) {
        String[] lines = puzzleInput.split(System.lineSeparator());
        String[] lineParts;
        List<List<Long>> data = new ArrayList<>(lines.length);
        List<Long> entry;
        for (String line : lines) {
            lineParts = line.split(" ");
            entry = new ArrayList<>(lineParts.length);
            for (String linePart : lineParts) {
                entry.add(Long.parseLong(linePart));
            }
            data.add(entry);
        }
        return data;
    }
}
