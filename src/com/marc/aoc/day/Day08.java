package com.marc.aoc.day;

import com.marc.aoc.common.AppearanceCounter;
import com.marc.aoc.common.Spot;
import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.LinesToCharGrid;

import java.util.*;

public class Day08 implements Day<char[][]> {

    private static boolean isWithin(int row, int column, int rows, int columns) {
        return  row >= 0 && column >= 0 && row < rows && column < columns;
    }

    @Override
    public int day() {
        return 8;
    }

    @Override
    public Deserializer<char[][]> deserializer() {
        return new LinesToCharGrid();
    }

    @Override
    public long partOne(char[][] input) {
        int rows = input.length;
        int columns = input[0].length;
        Map<Character, Set<Spot>> appearances = AppearanceCounter.countLetterAppearances(input);
        Set<Spot> antinodes = new HashSet<>();
        for (Set<Spot> antennas: appearances.values()) {
            for (Spot antenna : antennas) {
                for (Spot other: antennas) {
                    if (antenna == other) {
                        continue;
                    }
                    int offsetRow = antenna.row() - other.row();
                    int offsetColumn = antenna.column() - other.column();

                    int row = antenna.row() + offsetRow;
                    int column = antenna.column() + offsetColumn;
                    if (isWithin(row, column, rows, columns)) {
                        antinodes.add(new Spot(row, column));
                    }
                    row = other.row() - offsetRow;
                    column = other.column() - offsetColumn;
                    if (isWithin(row, column, rows, columns)) {
                        antinodes.add(new Spot(row, column));
                    }
                }
            }
        }
        return antinodes.size();
    }

    @Override
    public long partTwo(char[][] input) {
        int rows = input.length;
        int columns = input[0].length;
        Map<Character, Set<Spot>> appearances = AppearanceCounter.countLetterAppearances(input);
        Set<Spot> antinodes = new HashSet<>();
        for (Set<Spot> antennas: appearances.values()) {
            for (Spot antenna : antennas) {
                for (Spot other: antennas) {
                    if (antenna == other) {
                        continue;
                    }
                    int offsetRow = antenna.row() - other.row();
                    int offsetColumn = antenna.column() - other.column();

                    int row = antenna.row();
                    int column = antenna.column();
                    while (isWithin(row, column, rows, columns)) {
                        antinodes.add(new Spot(row, column));
                        row += offsetRow;
                        column += offsetColumn;
                    }
                    row = other.row();
                    column = other.column();
                    while (isWithin(row, column, rows, columns)) {
                        antinodes.add(new Spot(row, column));
                        row -= offsetRow;
                        column -= offsetColumn;
                    }
                }
            }
        }
        return antinodes.size();
    }
}
