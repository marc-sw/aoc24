package com.marc.aoc.day;

import com.marc.aoc.common.Bits;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Day07 implements Day {

    private static final BiFunction<Long, Long, Long> add = (x, y) -> x + y;
    private static final BiFunction<Long, Long, Long> multiply = (x, y) -> x * y;
    private static final BiFunction<Long, Long, Long> combine = (x, y) -> x * (long) Math.pow(10, (long) Math.log10(y) + 1) + y;

    private List<List<Long>> equations;

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

    private void logEquations() {
        for (List<Long> equation: equations) {
            for (long val : equation) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    private void logEquation(List<Long> equation, int combinations, int size) {
        System.out.printf("%d = %d", equation.get(0), equation.get(1));
        for (int i = 2; i < equation.size(); i++) {
            if (Bits.getBit(combinations, i - 2, size)) {
                System.out.print(" * ");
            } else {
                System.out.print(" + ");
            }
            System.out.print(equation.get(i));
        }
        System.out.println();
    }

    /**
     *
     * @param equation is the actual equation which has the expected solution at first index and the members after
     * @param combinations is the current binary wise state
     * @return = expected - actual
     */
    private long calculateDifference(List<Long> equation, int combinations, int size) {
        long expected = equation.get(0);
        long actual = equation.get(1);
        for (int i = 2; i < equation.size() && actual <= expected; i++) {
            if (Bits.getBit(combinations, i - 2, size)) {
                actual *= equation.get(i);
            } else {
                actual += equation.get(i);
            }
        }
        return expected - actual;
    }

    private boolean isTrue(List<Long> equation) {
        int size = equation.size() - 2;
        int combinations = 1 << size;
        int combination = 0;
        long difference;
        do {
            difference = calculateDifference(equation, combination, size);
            combination++;
        } while (difference != 0 && combination < combinations);
        return difference == 0;
    }

    @Override
    public int getNumber() {
        return 7;
    }

    @Override
    public void setup(String puzzleInput) {
        String[] lines = puzzleInput.split(System.lineSeparator());
        equations = new ArrayList<>(lines.length);
        for (String line: lines) {
            int colonIndex = line.indexOf(':');
            long result = Long.parseLong(line.substring(0, colonIndex));
            String[] members = line.substring(colonIndex + 2).split(" ");
            List<Long> equation = new ArrayList<>(members.length + 1);
            equation.add(result);
            for (String member: members) {
                equation.add(Long.parseLong(member));
            }
            equations.add(equation);
        }
    }

    @Override
    public long solvePartOne() {
        return sumCorrect(equations, List.of(add, multiply));
    }

    @Override
    public long solvePartTwo() {
        return sumCorrect(equations, List.of(add, multiply, combine));
    }
}
