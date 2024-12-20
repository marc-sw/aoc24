package com.marc.aoc.deserializer;

public class LinesToCharGrid implements Deserializer<char[][]>{

    @Override
    public char[][] deserialize(String puzzleInput) {
        String[] lines = puzzleInput.split(System.lineSeparator());
        int height = lines.length;
        int width = lines[0].length();
        char[][] grid = new char[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = lines[y].charAt(x);
            }
        }
        return grid;
    }
}
