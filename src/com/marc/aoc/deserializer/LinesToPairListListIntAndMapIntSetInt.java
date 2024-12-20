package com.marc.aoc.deserializer;

import com.marc.aoc.common.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LinesToPairListListIntAndMapIntSetInt implements Deserializer<Pair<List<List<Integer>>, Map<Integer, Set<Integer>>>> {

    @Override
    public Pair<List<List<Integer>>, Map<Integer, Set<Integer>>> deserialize(String puzzleInput) {
        String[] parts = puzzleInput.split(System.lineSeparator() + System.lineSeparator(), 2);
        return new Pair<>(new LinesToListListInt(",").deserialize(parts[1]), new LinesToMapIntSetInt().deserialize(parts[0]));
    }
}
