package com.marc.aoc.day;


import com.marc.aoc.deserializer.Deserializer;

public interface Day<T> {

    int day();

    Deserializer<T> deserializer();

    long partOne(T input);

    long partTwo(T input);
}
