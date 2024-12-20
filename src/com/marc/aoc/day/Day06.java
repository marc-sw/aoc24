package com.marc.aoc.day;

import com.marc.aoc.common.Direction;
import com.marc.aoc.common.Grids;
import com.marc.aoc.common.Move;
import com.marc.aoc.common.Spot;
import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.LinesToCharGrid;

import java.util.HashSet;
import java.util.Set;

public class Day06 implements Day<char[][]> {

    private static final char PATH = '.';
    private static final char OBSTACLE = '#';
    private static final char START = '^';

    @Override
    public int day() {
        return 6;
    }

    @Override
    public Deserializer<char[][]> deserializer() {
        return new LinesToCharGrid();
    }

    @Override
    public long partOne(char[][] input) {
        return trackPath(input).size();
    }

    @Override
    public long partTwo(char[][] input) {
        int r = 0;
        Spot start = findStart(input);
        Set<Spot> path = trackPath(input);
        path.remove(start);
        for (Spot spot: path) {
            Spot position = start.cloned();
            Spot ahead = position.cloned();
            Direction direction = Direction.UP;
            Set<Move> moves = new HashSet<>();
            Grids.set(spot, OBSTACLE, input);
            boolean loop = false;

            while (Grids.isWithin(position, input) && !loop) {
                ahead.add(direction);
                if (Grids.isWithin(ahead, input) && Grids.get(ahead, input) == OBSTACLE) {
                    moves.add(new Move(position.cloned(), direction));
                    direction = direction.turnRight();
                } else {
                    moves.add(new Move(position.cloned(), direction));
                    position.add(direction);
                }
                ahead.set(position);

                if (moves.contains(new Move(position, direction))) {
                    loop = true;
                    r++;
                }
            }
            Grids.set(spot, PATH, input);
        }
        return r;
    }

    private static Set<Spot> trackPath(char[][] input) {
        Spot position = findStart(input);
        Spot ahead = position.cloned();
        Direction direction = Direction.UP;
        Set<Spot> visited = new HashSet<>();
        visited.add(position.cloned());

        do {
            ahead.add(direction);
            if (Grids.isWithin(ahead, input) && Grids.get(ahead, input) == OBSTACLE) {
                direction = direction.turnRight();
            } else {
                position.add(direction);
                if (Grids.isWithin(position, input)) {
                    visited.add(position.cloned());
                }
            }
            ahead.set(position);
        } while (Grids.isWithin(position, input));
        return visited;
    }

    private static Spot findStart(char[][] input) {
        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < input[0].length; column++) {
                if (input[row][column] == START) {
                    return new Spot(row, column);
                }
            }
        }
        throw new RuntimeException("no start position found for guard");
    }
}
