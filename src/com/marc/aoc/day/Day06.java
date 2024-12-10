package com.marc.aoc.day;

import com.marc.aoc.common.Direction;
import com.marc.aoc.common.Spot;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class Day06 implements Day {

    private static final Supplier<Boolean> NO_CONDITION = () -> true;
    private static final Runnable NO_ACTION = () -> {};

    private int startRow;
    private int startColumn;
    private Set<Move> path;
    private Set<Move> loopPath;
    private Direction startDirection;
    private Spot guard;
    private Direction direction;
    private boolean[][] obstacles;
    private int loops;
    private int i = 0;

    private boolean isInside(Spot spot) {
        return spot.row() >= 0 && spot.column() >= 0 && spot.row() < obstacles.length && spot.column() < obstacles[0].length;
    }

    private boolean isObstacle(Spot spot) {
        return obstacles[spot.row()][spot.column()];
    }

    private boolean isStart(Spot spot) {
        return startRow == spot.row() && startColumn == spot.column();
    }

    private void simulatePath(Supplier<Boolean> condition, Runnable beforeMove) {
        path.clear();
        simulate(condition, () -> {
            path.add(new Move(guard.cloned(),direction));
            beforeMove.run();
        });
        path.add(new Move(guard.cloned(), direction));
    }

    private void simulateCompletePath() {
        guard = new Spot(startRow, startColumn);
        direction = startDirection;
        simulatePath(NO_CONDITION, NO_ACTION);
    }

    private void simulateLoop() {
        Spot obstacle = guard.cloned();
        obstacle.add(direction);
        if (!isInside(obstacle) || isStart(obstacle)) {
            return;
        }
        Move before = new Move(guard.cloned(), direction);
        loopPath.clear();
        obstacles[obstacle.row()][obstacle.column()] = true;
        simulate(() -> !loopPath.contains(new Move(guard, direction)), () -> loopPath.add(new Move(guard, direction)));
        obstacles[obstacle.row()][obstacle.column()] = false;
        if (loopPath.contains(new Move(guard, direction))) {
            loops++;
        }
        guard = before.spot;
        direction = before.direction;
        i++;
    }

    private void simulate(Supplier<Boolean> condition, Runnable beforeMove) {
        Spot ahead = guard.cloned();
        ahead.add(direction);
        while (condition.get() && isInside(ahead)) {
            if (isObstacle(ahead)) {
                direction = direction.turnRight();
            } else {
                beforeMove.run();
                guard.add(direction);
            }
            ahead.set(guard);
            ahead.add(direction);
        }
    }

    @Override
    public int getNumber() {
        return 6;
    }

    @Override
    public void setup(String puzzleInput) {
        String[] lines = puzzleInput.split(System.lineSeparator());
        obstacles = new boolean[lines.length][lines[0].length()];
        char character;
        for (int row = 0; row < obstacles.length; row++) {
            for (int column = 0; column < obstacles[0].length; column++) {
                character = lines[row].charAt(column);
                if (character == '#') {
                    obstacles[row][column] = true;
                } else if (character == '^') {
                    startRow = row;
                    startColumn = column;
                }
            }
        }
        startDirection = Direction.UP;
        path = new HashSet<>();
        loopPath = new HashSet<>();
    }

    @Override
    public long solvePartOne() {
        simulateCompletePath();
        return path.stream().map(move -> move.spot).collect(Collectors.toSet()).size();
    }

    @Override
    public long solvePartTwo() {
        guard = new Spot(startRow, startColumn);
        direction = startDirection;
        simulatePath(NO_CONDITION, this::simulateLoop);
        return loops;
    }

    record Move(Spot spot, Direction direction) {

    }
}
