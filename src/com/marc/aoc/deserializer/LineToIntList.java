package com.marc.aoc.deserializer;

import java.util.ArrayList;
import java.util.List;

public class LineToIntList implements Deserializer<List<Integer>> {

    @Override
    public List<Integer> deserialize(String puzzleInput) {
        String[] parts = puzzleInput.split(" ");
        List<Integer> data = new ArrayList<>(parts.length);
        for (String part: parts) {
            data.add(Integer.parseInt(part));
        }
        return data;
    }
}
