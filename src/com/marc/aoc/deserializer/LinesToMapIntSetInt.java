package com.marc.aoc.deserializer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LinesToMapIntSetInt implements Deserializer<Map<Integer, Set<Integer>>> {
    @Override
    public Map<Integer, Set<Integer>> deserialize(String puzzleInput) {
        String[] lines = puzzleInput.split(System.lineSeparator());
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (String line: lines) {
            int split = line.indexOf('|');
            int first = Integer.parseInt(line.substring(0, split));
            int second = Integer.parseInt(line.substring(split + 1));
            if (!map.containsKey(first)) {
                map.put(first, new HashSet<>());
            }
            map.get(first).add(second);
        }
        return map;
    }
}
