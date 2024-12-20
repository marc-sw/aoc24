package com.marc.aoc.day;

import com.marc.aoc.common.ClawMachine;
import com.marc.aoc.deserializer.ClawMachineDeserializer;
import com.marc.aoc.deserializer.Deserializer;

import java.util.List;

public class Day13 implements Day<List<ClawMachine>> {

    @Override
    public int day() {
        return 13;
    }

    @Override
    public Deserializer<List<ClawMachine>> deserializer() {
        return new ClawMachineDeserializer();
    }

    @Override
    public long partOne(List<ClawMachine> input) {
        return 0;
    }

    @Override
    public long partTwo(List<ClawMachine> input) {
        return 0;
    }
}
