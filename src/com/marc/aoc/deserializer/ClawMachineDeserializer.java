package com.marc.aoc.deserializer;

import com.marc.aoc.common.ClawMachine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClawMachineDeserializer implements Deserializer<List<ClawMachine>> {

    private ClawMachine stringToClawMachine(String raw) {
        return null;
    }

    @Override
    public List<ClawMachine> deserialize(String puzzleInput) {
        return Arrays
                .stream(puzzleInput.split(System.lineSeparator() + System.lineSeparator()))
                .map(this::stringToClawMachine)
                .collect(Collectors.toList());
    }
}
