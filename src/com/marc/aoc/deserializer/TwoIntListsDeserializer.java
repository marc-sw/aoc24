package com.marc.aoc.deserializer;

import com.marc.aoc.common.Pair;

import java.util.ArrayList;
import java.util.List;

public class TwoIntListsDeserializer implements Deserializer<Pair<List<Integer>, List<Integer>>> {

    @Override
    public Pair<List<Integer>, List<Integer>> deserialize(String puzzleInput) {
        String[] lines = puzzleInput.split(System.lineSeparator());
        List<Integer> left = new ArrayList<>(lines.length);
        List<Integer> right = new ArrayList<>(lines.length);
        int end = lines[0].indexOf(' ');
        for (String line: lines) {
            left.add(Integer.parseInt(line.substring(0, end)));
            right.add(Integer.parseInt(line.substring(end + 3)));
        }
        return new Pair<>(left, right);
    }
}
