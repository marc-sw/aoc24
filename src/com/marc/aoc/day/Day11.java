package com.marc.aoc.day;


import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.LineToIntList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 implements Day<List<Integer>> {

    private static final Map<Long, Map<Byte, Long>> cache = new HashMap<>();

    private static long getStoneCount(long stone, byte blinks) {
        if (!cache.containsKey(stone)) {
            cache.put(stone, new HashMap<>());
        }
        if (!cache.get(stone).containsKey(blinks)) {
            return 0;
        }
        return cache.get(stone).get(blinks);
    }

    private static void setStoneCount(long stone, byte blinks, long value) {
        if (!cache.containsKey(stone)) {
            cache.put(stone, new HashMap<>());
        }
        cache.get(stone).put(blinks, value);
    }

    private static long countStones(long stone, byte blinks) {
        if (blinks == 0) {
            return 1;
        }
        long count = getStoneCount(stone, blinks);
        if (count != 0) {
            return count;
        }
        byte newBlinks = (byte) (blinks - 1);
        if (stone == 0) {
            long result = countStones(1L, newBlinks);
            setStoneCount(stone, blinks, result);
            return result;
        }
        int digits = 0;
        long cloned = stone;
        while (cloned > 0) {
            cloned /= 10;
            digits++;
        }
        if (digits % 2 != 0) {
            long result = countStones(stone * 2024, newBlinks);
            setStoneCount(stone, blinks, result);
            return result;
        };
        long factor = 1;
        for (int i = 0; i < digits / 2; i++) {
            factor *= 10;
        }
        long first = stone / factor;
        long second = stone - first * factor;
        long result = countStones(first, newBlinks) + countStones(second, newBlinks);
        setStoneCount(stone, blinks, result);
        return result;
    }

    private static long solve(List<Integer> input, byte blinks) {
        long total = 0;
        for (int val: input) {
            total += countStones(val, blinks);
        }
        return total;
    }

    @Override
    public int day() {
        return 11;
    }

    @Override
    public Deserializer<List<Integer>> deserializer() {
        return new LineToIntList();
    }

    @Override
    public long partOne(List<Integer> input) {
        return solve(input, (byte) 25);
    }

    @Override
    public long partTwo(List<Integer> input) {
        return solve(input, (byte) 75);
    }
}
