package com.marc.aoc;

import com.marc.aoc.day.Day;
import com.marc.aoc.day.Solution;
import com.marc.aoc.deserialization.Deserializer;

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

    public static <T> void run(Solution<T> solution, Deserializer<T> deserializer, boolean test) {
        String puzzleInput = InputSupplier.supply(solution.getDay(), test);
        T input = deserializer.deserialize(puzzleInput);
        long result = solution.solve(input);
        System.out.printf("%s Day %d Part %d\n", test ? "Testing" : "Running", solution.getDay(), solution.getPart());
        if (test) {
            System.out.printf("Expected: %d Actual: %d\n", solution.getTestSolution(), result);
            System.out.println("Test " + (result == solution.getTestSolution() ? "successful": "failed"));
        } else {
            System.out.printf("Solution: %d", result);
        }
    }
}
