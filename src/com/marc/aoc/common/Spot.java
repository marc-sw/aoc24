package com.marc.aoc.common;

import java.util.Objects;

public class Spot {

    private int row;
    private int column;

    public Spot(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Spot(Spot original) {
        this(original.row(), original.column());
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public void add(Spot other) {
        row += other.row;
        column += other.column;
    }

    public void add(Spot other, int scale) {
        row += other.row * scale;
        column += other.column * scale;
    }

    public void add(Direction direction) {
        row += direction.y;
        column += direction.x;
    }

    public void set(Spot spot) {
        row = spot.row;
        column = spot.column;
    }

    public Spot cloned() {
        return new Spot(row, column);
    }

    @Override
    public String toString() {
        return "Spot{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spot spot = (Spot) o;
        return row == spot.row && column == spot.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
