package com.marc.aoc.deserialization;

public interface Deserializer<T> {

    T deserialize(String puzzleInput);

}
