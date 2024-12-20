package com.marc.aoc.deserializer;

public class LineToIntArray implements Deserializer<int[]> {

    @Override
    public int[] deserialize(String puzzleInput) {
        int[] array = new int[puzzleInput.length()];
        for (int i = 0; i < array.length; i++) {
            array[i] = puzzleInput.charAt(i) - '0';
        }
        return array;
    }
}
