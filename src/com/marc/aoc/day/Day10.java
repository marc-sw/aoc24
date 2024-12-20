package com.marc.aoc.day;

import com.marc.aoc.common.Direction;
import com.marc.aoc.common.Spot;
import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.LinesToByteGrid;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Day10 implements Day<byte[][]> {

    private static boolean isWithin(byte[][] grid, int row, int column) {
        return row >= 0 && column >= 0 && row < grid.length && column < grid[0].length;
    }

    private static void addValidCandidates(byte[][] input, Queue<Spot> queue, Spot spot) {
        int row;
        int col;
        for (Direction direction: Direction.BASIC) {
            row = spot.row() + direction.y;
            col = spot.column() + direction.x;
            if (isWithin(input, row, col) && input[row][col] - input[spot.row()][spot.column()] == 1) {
                queue.add(new Spot(row, col));
            }
        }
    }

    @Override
    public int day() {
        return 10;
    }

    @Override
    public Deserializer<byte[][]> deserializer() {
        return new LinesToByteGrid();
    }

    @Override
    public long partOne(byte[][] input) {
        int total = 0;
        Set<Spot> visitedNines = new HashSet<>();
        Queue<Spot> queue = new LinkedList<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length; x++) {
                if (input[y][x] != 0) {
                    continue;
                }
                queue.clear();
                visitedNines.clear();
                addValidCandidates(input, queue, new Spot(y, x));
                while (!queue.isEmpty()) {
                    Spot spot = queue.poll();
                    if (input[spot.row()][spot.column()] == 9) {
                        visitedNines.add(spot);
                    } else {
                        addValidCandidates(input, queue, spot);
                    }
                }
                total += visitedNines.size();
            }
        }
        return total;
    }

    @Override
    public long partTwo(byte[][] input) {
        int total = 0;
        Queue<Spot> queue = new LinkedList<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length; x++) {
                if (input[y][x] != 0) {
                    continue;
                }
                queue.clear();
                addValidCandidates(input, queue, new Spot(y, x));
                while (!queue.isEmpty()) {
                    Spot spot = queue.poll();
                    if (input[spot.row()][spot.column()] == 9) {
                        total++;
                    } else {
                        addValidCandidates(input, queue, spot);
                    }
                }
            }
        }
        return total;
    }
}
