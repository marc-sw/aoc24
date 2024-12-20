package com.marc.aoc.deserializer;

import java.util.ArrayList;
import java.util.List;

public class LinesToListListInt implements Deserializer<List<List<Integer>>> {

    private final String seperator;

    public LinesToListListInt(String seperator) {
        this.seperator = seperator;
    }

    @Override
    public List<List<Integer>> deserialize(String puzzleInput) {
        String[] lines = puzzleInput.split(System.lineSeparator());
        String[] lineParts;
        List<List<Integer>> data = new ArrayList<>(lines.length);
        List<Integer> entry;
        for (String line : lines) {
            lineParts = line.split(seperator);
            entry = new ArrayList<>(lineParts.length);
            for (String linePart : lineParts) {
                entry.add(Integer.parseInt(linePart));
            }
            data.add(entry);
        }
        return data;
    }
}
