package com.marc.aoc.deserializer;

public class LinesToByteGrid implements Deserializer<byte[][]>{

    @Override
    public byte[][] deserialize(String puzzleInput) {
        String[] lines = puzzleInput.split(System.lineSeparator());
        int height = lines.length;
        int width = lines[0].length();
        byte[][] grid = new byte[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = (byte) (lines[y].charAt(x) - '0');
            }
        }
        return grid;
    }
}
