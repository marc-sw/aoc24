package com.marc.aoc.common;

public class Grids {

    public static boolean isWithin(int row, int column, int rows, int columns) {
        return row >= 0 && column >= 0 && row < rows && column < columns;
    }

    public static boolean isWithin(int row, int column, char[][] grid) {
        return isWithin(row, column, grid.length, grid[0].length);
    }

    public static boolean isWithin(Spot spot, char[][] grid) {
        return isWithin(spot.row(), spot.column(), grid);
    }

    public static char get(Spot spot, char[][] grid) {
        return grid[spot.row()][spot.column()];
    }

    public static void set(Spot spot, char val, char[][] grid) {
        grid[spot.row()][spot.column()] = val;
    }
}
