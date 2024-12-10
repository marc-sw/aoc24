package com.marc.aoc.common;

public class BinaryCombinations {

    private final int size;
    private int data;
    private int combinations;
    private int combinationLow;
    private int combinationHigh;

    public BinaryCombinations(int elements) {
        size = elements;
        combinations = 1 << elements;
        combinationLow = 0;
        combinationHigh = combinations - 1;
        data = combinations >> 1;
    }

    public int getSize() {
        return size;
    }

    public boolean getBit(int index) {
        return ((data >> (size - 1 - index)) & 1) == 1;
    }

    public void increase() {
        combinationLow = data + 1;
        data = (combinationHigh + combinationLow) / 2;
    }

    public void decrease() {
        combinationHigh = data - 1;
        data = (combinationHigh + combinationLow) / 2;
    }

    public boolean hasRemaining() {
        return combinationLow <= combinationHigh;
    }
}
