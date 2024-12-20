package com.marc.aoc.common;

public class ClawMachine {

    private final Vec2 buttonOffsetA;
    private final Vec2 buttonOffsetB;
    private final Vec2 priceLocation;

    public ClawMachine(Vec2 buttonOffsetA, Vec2 buttonOffsetB, Vec2 priceLocation) {
        this.buttonOffsetA = buttonOffsetA;
        this.buttonOffsetB = buttonOffsetB;
        this.priceLocation = priceLocation;
    }

    public Vec2 getButtonOffsetA() {
        return buttonOffsetA;
    }

    public Vec2 getButtonOffsetB() {
        return buttonOffsetB;
    }

    public Vec2 getPriceLocation() {
        return priceLocation;
    }
}
