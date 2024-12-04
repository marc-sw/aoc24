package com.marc.aoc.day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 implements Day {

    private List<Integer> sortedLeft;
    private List<Integer> sortedRight;

    @Override
    public int getNumber() {
        return 1;
    }

    @Override
    public void setup(String puzzleInput) {
        String[] lineParts;
        String[] lines = puzzleInput.split(System.lineSeparator());
        sortedLeft = new ArrayList<>(lines.length);
        sortedRight = new ArrayList<>(lines.length);
        for (String line: lines) {
            if (!line.isBlank()) {
                lineParts = line.split(" {3}", 2);
                sortedLeft.add(Integer.parseInt(lineParts[0].strip()));
                sortedRight.add(Integer.parseInt(lineParts[1].strip()));
            }
        };
        Collections.sort(sortedLeft);
        Collections.sort(sortedRight);
    }

    @Override
    public int solvePartOne() {
        int totalDistance = 0;
        for (int i = 0; i < sortedLeft.size(); i++) {
            totalDistance += Math.abs(sortedLeft.get(i) - sortedRight.get(i));
        }
        return totalDistance;
    }

    @Override
    public int solvePartTwo() {
        int leftCount;
        int rightCount;
        int number;
        int total = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < sortedLeft.size() && rightIndex < sortedRight.size()) {
            if (sortedLeft.get(leftIndex) < sortedRight.get(rightIndex)) {
                leftIndex++;
            } else if (sortedLeft.get(leftIndex) > sortedRight.get(rightIndex)) {
                rightIndex++;
            } else {
                leftCount = 0;
                rightCount = 0;
                number = sortedLeft.get(leftIndex);
                while (leftIndex < sortedLeft.size() && sortedLeft.get(leftIndex) == number) {
                    leftCount++;
                    leftIndex++;
                }
                while (rightIndex < sortedRight.size() && sortedRight.get(rightIndex) == number) {
                    rightCount++;
                    rightIndex++;
                }
                total += number * leftCount * rightCount;
            }
        }
        return total;
    }
}
