package com.marc.aoc.day;

import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.LinesToListListLong;

import java.util.List;
import java.util.function.BiFunction;

public class Day07 implements Day<List<List<Long>>> {

    private static final BiFunction<Long, Long, Long> add = (x, y) -> x + y;
    private static final BiFunction<Long, Long, Long> multiply = (x, y) -> x * y;
    private static final BiFunction<Long, Long, Long> combine = (x, y) -> x * (long) Math.pow(10, (long) Math.log10(y) + 1) + y;

    private static boolean solve(List<Long> equation, List<BiFunction<Long, Long, Long>> operators, int eqOffset, long actual) {
        if (actual > equation.get(0)) {
            return false;
        }
        if (eqOffset >= equation.size()) {
            return actual == equation.get(0);
        }
        for (BiFunction<Long, Long, Long> operator: operators) {
            if (solve(equation, operators, eqOffset + 1, operator.apply(actual, equation.get(eqOffset)))) {
                return true;
            }
        }
        return false;
    }

    private static long sumCorrect(List<List<Long>> equations, List<BiFunction<Long, Long, Long>> operators) {
        long total = 0;
        for  (List<Long> equation: equations) {
            if (solve(equation, operators, 2, equation.get(1))) {
                total += equation.get(0);
            }
        }
        return total;
    }

    @Override
    public int day() {
        return 7;
    }

    @Override
    public Deserializer<List<List<Long>>> deserializer() {
        return input -> {
            input = input.replaceAll(":", "");
            return new LinesToListListLong().deserialize(input);
        };
    }

    @Override
    public long partOne(List<List<Long>> input) {
        return sumCorrect(input, List.of(add, multiply));
    }

    @Override
    public long partTwo(List<List<Long>> input) {
        return sumCorrect(input, List.of(add, multiply, combine));
    }
}
