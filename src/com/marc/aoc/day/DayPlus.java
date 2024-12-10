package com.marc.aoc.day;

public interface DayPlus<T> {

    int getDay();
    long getPartOneTestResult();
    long getPartTwoTestResult();
    T deserialize(String puzzleInput);
    long solvePartOne(T input);
    long solvePartTwo(T input);

}
