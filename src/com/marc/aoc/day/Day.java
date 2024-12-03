package com.marc.aoc.day;

import com.marc.aoc.InputSupplier;

public abstract class Day {

    protected final int dayNumber;

    public Day(int dayNumber, boolean test) {
        this.dayNumber = dayNumber;
        setupTextInput(InputSupplier.supply(dayNumber, test));
    }

    public void runPartOne() {
        System.out.printf("Running Day %d Part 1\n", dayNumber);
        System.out.printf("Solution: %d\n", solvePartOne());
    }

    public void runPartTwo() {
        System.out.printf("Running Day %d Part 2\n", dayNumber);
        System.out.printf("Solution: %d\n", solvePartTwo());
    }

    public void runBoth() {
        runPartOne();
        System.out.println();
        runPartTwo();
    }

    protected abstract void setupTextInput(String textInput);

    protected abstract int solvePartOne();

    protected abstract int solvePartTwo();
}
