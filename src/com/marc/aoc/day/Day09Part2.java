package com.marc.aoc.day;

import java.util.Arrays;

public class Day09Part2 implements Solution<byte[]> {
    @Override
    public int getDay() {
        return 9;
    }

    @Override
    public int getPart() {
        return 2;
    }

    @Override
    public long getTestSolution() {
        return 2858;
    }

    private static int sumTill(byte[] input, int index) {
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum += input[i];
        }
        return sum;
    }

    //2333133121414131402
    //00...111...2...333.44.5555.6666.777.888899

    @Override
    public long solve(byte[] input) {
        byte[] spaceDistribution = input.clone();
        long result = 0;

        int start = input.length - 1;
        if (start % 2 != 0) {
            start--;
        }
        int spaceStart = 1;
        for (int i = start; i > 1; i -= 2) {
            boolean inserted = false;
            for (int j = spaceStart; j < i && !inserted; j += 2) {
                if (spaceDistribution[j] >= input[i]) {
                    int resultIndex = sumTill(spaceDistribution, j);
                    int size = input[i];
                    for (int k = resultIndex; k < resultIndex + size; k++) {
                        result += (long) k * (i / 2);
                    }
                    spaceDistribution[i] = 0;
                    spaceDistribution[j] -= (byte) size;
                    spaceDistribution[j - 1] += (byte) size;
                    inserted = true;
                }
            }
            if (!inserted) {
                int index = sumTill(spaceDistribution, i);
                for (int j = index; j < index + input[i]; j++) {
                    result += (long) j * (i / 2);
                }
            }
        }
        return result;
    }
}
