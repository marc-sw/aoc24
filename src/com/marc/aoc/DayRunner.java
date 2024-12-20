package com.marc.aoc;

import com.marc.aoc.common.Pair;
import com.marc.aoc.day.Day;

import java.util.function.Function;

public class DayRunner {

    private static void display(Pair<Long, Long> resultTime) {
        System.out.println("Result: " + resultTime.first);
        System.out.println("Time: " + resultTime.second + "ms");
    }

    public static <T> void first(Day<T> day, boolean testing) {
        Pair<Long, Long> resultTime = timed(day::partOne, day.deserializer().deserialize(InputSupplier.supply(day.day(), testing)));
        System.out.printf("Day %d Part 1\n", day.day());
        display(resultTime);
    }

    public static <T> void second(Day<T> day, boolean testing) {
        Pair<Long, Long> resultTime = timed(day::partTwo, day.deserializer().deserialize(InputSupplier.supply(day.day(), testing)));
        System.out.printf("Day %d Part 2\n", day.day());
        display(resultTime);
    }

    public static <T> void both(Day<T> day, boolean testing) {
        first(day, testing);
        System.out.println();
        second(day, testing);
    }

    private static <T> Pair<Long, Long> timed(Function<T, Long> solution, T input) {
        long start = System.currentTimeMillis();
        long result = solution.apply(input);
        long end = System.currentTimeMillis();
        return new Pair<>(result, end - start);
    }
}
