package com.marc.aoc.common;

public class Bits {

    public static boolean getBit(int data, int index) {
        return (data >> (31 - index) & 1) == 1;
    }

    public static boolean getBit(int data, int index, int size) {
        return getBit(data, 32 - size + index);
    }

    public static void printBits(int data) {
        for (int i = 31; i >= 0; i--) {
            System.out.print(data >> i & 1);
        }
        System.out.println();
    }

    public static boolean getBit(long data, int index) {
        return (data >> (63 - index) & 1) == 1;
    }

    public static boolean getBit(long data, int index, int size) {
        return getBit(data, 64 - size + index);
    }

    public static void printBits(long data) {
        for (int i = 63; i >= 0; i--) {
            System.out.print(data >> i & 1);
        }
        System.out.println();
    }
}
