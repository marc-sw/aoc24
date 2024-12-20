package com.marc.aoc.day;

import com.marc.aoc.common.Direction;
import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.LinesToCharGrid;

public class Day04 implements Day<char[][]> {

    private static final char[] query = {'X', 'M', 'A', 'S'};

    private static boolean isOutside(int x, int y, int rows, int columns) {
        return x < 0 || y < 0 || x >= columns || y >= rows;
    }

    private static boolean isCorrect(Direction direction, int x, int y, char[][] charGrid) {
        for (int i = 1; i < query.length; i++) {
            x += direction.x;
            y += direction.y;
            if (isOutside(x, y, charGrid.length, charGrid[0].length)) {
                return false;
            }
            if (charGrid[y][x] != query[i]) {
                return false;
            }
        }
        return true;
    }

    private static int countXmas(int row, int col, char[][] charGrid) {
        int xmasCount = 0;
        if (charGrid[row][col] != query[0]) {
            return 0;
        }
        for (Direction direction: Direction.ALL) {
            if (isCorrect(direction, col, row, charGrid)) {
                xmasCount++;
            }
        }

        return xmasCount;
    }

    private static boolean isCrossMas(int row, int col, char[][] charGrid) {
        if (charGrid[row][col] != 'A') {
            return false;
        }
        if (isOutside(col + 1, row + 1, charGrid.length, charGrid[0].length) || isOutside(col - 1, row - 1, charGrid.length, charGrid[0].length)) {
            return false;
        }
        char topLeft = charGrid[row + Direction.UP_LEFT.y][col + Direction.UP_LEFT.x];
        char topRight = charGrid[row + Direction.UP_RIGHT.y][col + Direction.UP_RIGHT.x];
        char downLeft = charGrid[row + Direction.DOWN_LEFT.y][col + Direction.DOWN_LEFT.x];
        char downRight = charGrid[row + Direction.DOWN_RIGHT.y][col + Direction.DOWN_RIGHT.x];
        int sum = topLeft + topRight + downRight + downLeft;
        return topLeft != downRight && topRight != downLeft && sum == 320;
    }

    @Override
    public int day() {
        return 4;
    }

    @Override
    public Deserializer<char[][]> deserializer() {
        return new LinesToCharGrid();
    }

    @Override
    public long partOne(char[][] input) {
        int totalXmas = 0;
        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < input[row].length; column++) {
                totalXmas += countXmas(row, column, input);
            }
        }
        return totalXmas;
    }

    @Override
    public long partTwo(char[][] input) {
        int totalXmas = 0;
        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < input[row].length; column++) {
                if (isCrossMas(row, column, input)) {
                    totalXmas++;
                }
            }
        }
        return totalXmas;
    }
}
