package com.marc.aoc.day;


public interface Day {

    void setup(String puzzleInput);

    int getNumber();

    int solvePartOne();

    int solvePartTwo();
}
