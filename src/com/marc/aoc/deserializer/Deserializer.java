package com.marc.aoc.deserializer;

public interface Deserializer<T> {

    T deserialize(String puzzleInput);

}
