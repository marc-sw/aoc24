package com.marc.aoc.day;


public interface Day {

    int getNumber();

    void setup(String puzzleInput);

    int solvePartOne();

    int solvePartTwo();
}
