package com.marc.aoc.day;

import com.marc.aoc.common.Pair;
import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.TwoIntListsDeserializer;

import java.util.Collections;
import java.util.List;

public class Day01 implements Day<Pair<List<Integer>, List<Integer>>> {

    @Override
    public int day() {
        return 1;
    }

    @Override
    public Deserializer<Pair<List<Integer>, List<Integer>>> deserializer() {
        return new TwoIntListsDeserializer();
    }

    @Override
    public long partOne(Pair<List<Integer>, List<Integer>> input) {
        Collections.sort(input.first);
        Collections.sort(input.second);
        long totalDistance = 0;
        for (int i = 0; i < input.first.size(); i++) {
            totalDistance += Math.abs(input.first.get(i) - input.second.get(i));
        }
        return totalDistance;
    }

    @Override
    public long partTwo(Pair<List<Integer>, List<Integer>> input) {
        List<Integer> left = input.first;
        List<Integer> right = input.second;
        Collections.sort(left);
        Collections.sort(right);

        int leftCount;
        int rightCount;
        int value;
        int total = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            rightCount = 0;
            leftCount = 1;
            value = left.get(leftIndex++);
            while (leftIndex < left.size() && left.get(leftIndex) == value) {
                leftIndex++;
                leftCount++;
            }
            while (rightIndex < right.size() && right.get(rightIndex) < value) {
                rightIndex++;
            }
            while (rightIndex < right.size() && right.get(rightIndex) == value) {
                rightCount++;
                rightIndex++;
            }
            total += leftCount * rightCount * value;
        }
        return total;
    }
}
