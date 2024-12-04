package com.marc.aoc;

import com.marc.aoc.day.Day;

public class DayRunner {

    public static void run(Day day, boolean testing) {
        day.setup(InputSupplier.supply(day.getNumber(), testing));

        String action = testing ? "Testing": "Running";

        System.out.printf("%s Day %d Part 1\n", action, day.getNumber());
        System.out.printf("Solution: %d\n", day.solvePartOne());
        System.out.println();
        System.out.printf("%s Day %d Part 2\n", action, day.getNumber());
        System.out.printf("Solution: %d\n", day.solvePartTwo());
    }
}
