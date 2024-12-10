package com.marc.aoc.day;

import com.marc.aoc.common.Spot;

import java.util.*;
import java.util.function.BiConsumer;

public class Day08 implements Day {

    private int rows;
    private int columns;
    private Set<Spot> antinodes;
    private Map<Character, Set<Spot>> antennaMap;

    private boolean isWithin(int row, int column) {
        return  row >= 0 && column >= 0 && row < rows && column < columns;
    }

    private long solve(BiConsumer<Spot, Spot> processor) {
        antinodes = new HashSet<>();
        for (Set<Spot> antennas: antennaMap.values()) {
            for (Spot antenna : antennas) {
                for (Spot other: antennas) {
                    if (antenna == other) {
                        continue;
                    }
                    processor.accept(antenna, other);
                }
            }
        }
        return antinodes.size();
    }

    private void process(Spot antenna, Spot other) {
        int offsetRow = antenna.row() - other.row();
        int offsetColumn = antenna.column() - other.column();

        int row = antenna.row() + offsetRow;
        int column = antenna.column() + offsetColumn;
        if (isWithin(row, column)) {
            antinodes.add(new Spot(row, column));
        }
        row = other.row() - offsetRow;
        column = other.column() - offsetColumn;
        if (isWithin(row, column)) {
            antinodes.add(new Spot(row, column));
        }
    }

    private void processInLine(Spot antenna, Spot other) {
        int offsetRow = antenna.row() - other.row();
        int offsetColumn = antenna.column() - other.column();

        int row = antenna.row();
        int column = antenna.column();
        while (isWithin(row, column)) {
            antinodes.add(new Spot(row, column));
            row += offsetRow;
            column += offsetColumn;
        }
        row = other.row();
        column = other.column();
        while (isWithin(row, column)) {
            antinodes.add(new Spot(row, column));
            row -= offsetRow;
            column -= offsetColumn;
        }
    }

    @Override
    public int getNumber() {
        return 8;
    }

    @Override
    public void setup(String puzzleInput) {
        antennaMap = new HashMap<>();
        String[] lines = puzzleInput.split(System.lineSeparator());
        rows = lines.length;
        columns = lines[0].length();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                char c = lines[row].charAt(column);
                if (c == '.') {
                    continue;
                }
                if (!antennaMap.containsKey(c)) {
                    antennaMap.put(c, new HashSet<>());
                }
                antennaMap.get(c).add(new Spot(row, column));
            }
        }
    }

    @Override
    public long solvePartOne() {
        return solve(this::process);
    }

    @Override
    public long solvePartTwo() {
        return solve(this::processInLine);
    }
}
