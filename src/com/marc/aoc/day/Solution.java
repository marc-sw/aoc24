package com.marc.aoc.day;

public interface Solution<T> {

    int getDay();
    int getPart();
    long getTestSolution();
    long solve(T input);
}
