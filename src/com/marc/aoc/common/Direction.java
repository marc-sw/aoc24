package com.marc.aoc.common;

public enum Direction {

    UP(0, -1),
    UP_RIGHT(1, -1),
    RIGHT(1, 0),
    DOWN_RIGHT(1, 1),
    DOWN(0, 1),
    DOWN_LEFT(-1, 1),
    LEFT(-1, 0),
    UP_LEFT(-1, -1);


    public static final Direction[] ALL;
    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static {
        ALL = Direction.values();
    }

    public Direction turnRight() {
        return ALL[(this.ordinal() + 2) % ALL.length];
    }

    public boolean isVertical() {
        return ordinal() == 0 || ordinal() == 4;
    }

    public boolean isHorizontal() {
        return ordinal() == 2 || ordinal() == 6;
    }
}
