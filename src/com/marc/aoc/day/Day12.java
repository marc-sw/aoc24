package com.marc.aoc.day;

import com.marc.aoc.common.Direction;
import com.marc.aoc.common.Pair;
import com.marc.aoc.common.Spot;
import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.LinesToCharGrid;

import java.util.LinkedList;
import java.util.Queue;

public class Day12 implements Day<char[][]> {

    private static boolean isWithin(int x, int y, char[][] input) {
        return x >= 0 && y >= 0 && y < input.length && x < input[0].length;
    }

    private static boolean isWithin(Spot spot, char[][] input) {
        return isWithin(spot.column(), spot.row(), input);
    }

    private static char get(Spot spot, char[][] input) {
        return input[spot.row()][spot.column()];
    }

    private static long breathSearchPerimeterPrice(int x, int y, char[][] input, boolean[][] visited) {
        long area = 1;
        long perimeter = 0;
        char areaChar = input[y][x];
        visited[y][x] = true;
        Queue<Spot> queue = new LinkedList<>();
        queue.add(new Spot(y, x));

        while (!queue.isEmpty()) {
            Spot pos = queue.poll();
            for (Direction direction: Direction.BASIC) {
                Spot spot = pos.cloned();
                spot.add(direction);
                if (!isWithin(spot.column(), spot.row(), input)) {
                    perimeter++;
                    continue;
                }
                if (input[spot.row()][spot.column()] != areaChar) {
                    perimeter++;
                } else if (!visited[spot.row()][spot.column()]) {
                    queue.add(spot);
                    area++;
                    visited[spot.row()][spot.column()] = true;
                }
            }
        }
        return area * perimeter;
    }

    // returns the ammount of sides the border frame has of the region that contains the coordinates x y
    private static Pair<Integer, Integer> fillBorder(int x, int y, char[][] input, boolean[][] visited) {
        int area = 1;
        int sides = 0;
        char regionChar = input[y][x];
        Spot pos = new Spot(y, x);
        Spot ahead = pos.cloned();
        ahead.add(Direction.UP);
        while (isWithin(ahead, input) && get(ahead, input) == regionChar) {
            pos.set(ahead);
            ahead.add(Direction.UP);
        }
        visited[pos.row()][pos.column()] = true;
        Spot start = pos.cloned();
        Direction startDirection = Direction.RIGHT;
        Direction left = Direction.UP;
        Direction direction = Direction.RIGHT;
        Spot leftPos;

        do {
            leftPos = pos.cloned();
            leftPos.add(left);
            if (isWithin(leftPos, input) && get(leftPos, input) == regionChar) {
                direction = left;
                left = direction.turnLeft();
                sides++;
                continue;
            }
            ahead = pos.cloned();
            ahead.add(direction);
            if (isWithin(ahead, input) && get(ahead, input) == regionChar) {
                pos.add(direction);
                visited[pos.row()][pos.column()] = true;
                area++;
                continue;
            }
            left = direction;
            direction = direction.turnRight();
            sides++;
        } while (direction != startDirection && !pos.equals(start));
        return new Pair<>(area, sides);
    }

    private static Pair<Integer, Integer> breathSearchSidePrice(int x, int y, char[][] input, boolean[][] visited) {
        Pair<Integer, Integer> temp = fillBorder(x, y, input, visited);
        int area = temp.first;
        int sides;
        int borderSides = sides = temp.second;
        char areaChar = input[y][x];
        visited[y][x] = true;
        Queue<Spot> queue = new LinkedList<>();
        queue.add(new Spot(y, x));

        while (!queue.isEmpty()) {
            Spot pos = queue.poll();
            for (Direction direction: Direction.BASIC) {
                Spot spot = pos.cloned();
                spot.add(direction);
                if (!isWithin(spot, input)) {
                    continue;
                }
                if (input[spot.row()][spot.column()] == areaChar && !visited[spot.row()][spot.column()]) {
                    queue.add(spot);
                    area++;
                    visited[spot.row()][spot.column()] = true;
                }
            }
        }

        return new Pair<>(sides * area, borderSides);
    }

    @Override
    public int day() {
        return 12;
    }

    @Override
    public Deserializer<char[][]> deserializer() {
        return new LinesToCharGrid();
    }

    @Override
    public long partOne(char[][] input) {
        boolean[][] visited = new boolean[input.length][input[0].length];
        long sum = 0;
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length; x++) {
                if (!visited[y][x]) {
                    sum += breathSearchPerimeterPrice(x, y, input, visited);
                }
            }
        }
        return sum;
    }

    @Override
    public long partTwo(char[][] input) {
        boolean[][] visited = new boolean[input.length][input[0].length];
        long sum = 0;
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length; x++) {
                if (!visited[y][x]) {
                    //sum += breathSearchSidePrice(x, y, input, visited);
                }
            }
        }
        return sum;
    }
}
