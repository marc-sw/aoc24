package com.marc.aoc.day;

import com.marc.aoc.common.Direction;

public class Day04 implements Day {

    private static final char[] query = {'X', 'M', 'A', 'S'};
    private char[][] charGrid;

    private boolean isWithin(int x, int y) {
        return  x >= 0 && y >= 0 && x < charGrid[0].length && y < charGrid.length;
    }

    private boolean isCorrect(Direction direction, int x, int y) {
        for (int i = 1; i < query.length; i++) {
            x += direction.x;
            y += direction.y;
            if (!isWithin(x, y)) {
                return false;
            }
            if (charGrid[y][x] != query[i]) {
                return false;
            }
        }
        return true;
    }

    private int countXmas(int row, int col) {
        int xmasCount = 0;
        if (charGrid[row][col] != query[0]) {
            return 0;
        }
        for (Direction direction: Direction.ALL) {
            if (isCorrect(direction, col, row)) {
                xmasCount++;
            }
        }

        return xmasCount;
    }

    private boolean isCrossMas(int row, int col) {
        if (charGrid[row][col] != 'A') {
            return false;
        }
        if (!isWithin(col + 1, row + 1) || !isWithin(col - 1, row - 1)) {
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
    public void setup(String puzzleInput) {
        String[] lines = puzzleInput.split(System.lineSeparator());
        int rows = lines.length;
        int columns = lines[0].length();
        charGrid = new char[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                charGrid[row][column] = lines[row].charAt(column);
            }
        }
    }

    @Override
    public int getNumber() {
        return 4;
    }

    @Override
    public int solvePartOne() {
        int totalXmas = 0;
        for (int row = 0; row < charGrid.length; row++) {
            for (int column = 0; column < charGrid[row].length; column++) {
                totalXmas += countXmas(row, column);
            }
        }
        return totalXmas;
    }

    @Override
    public int solvePartTwo() {
        int totalXmas = 0;
        for (int row = 0; row < charGrid.length; row++) {
            for (int column = 0; column < charGrid[row].length; column++) {
                if (isCrossMas(row, column)) {
                    totalXmas++;
                }
            }
        }
        return totalXmas;
    }
}
