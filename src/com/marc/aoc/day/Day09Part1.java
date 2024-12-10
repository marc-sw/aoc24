package com.marc.aoc.day;

public class Day09Part1 implements Solution<byte[]> {

    @Override
    public int getDay() {
        return 9;
    }

    @Override
    public int getPart() {
        return 1;
    }

    @Override
    public long getTestSolution() {
        return 1928;
    }

    private static int calculateOutputSize(byte[] input) {
        int size = 0;
        for (int i = 0; i < input.length; i += 2) {
            size += input[i];
        }
        return size;
    }

    @Override
    public long solve(byte[] input) {
        short[] resultArray = new short[calculateOutputSize(input)];
        int inputIndex = 0;
        int resultIndex = 0;
        int inputFillIndex = input.length - 1;
        if (inputFillIndex % 2 != 0) {
            inputFillIndex--;
        }

        while (inputIndex <= inputFillIndex) {
            while (input[inputIndex] > 0) {
                resultArray[resultIndex++] = (short) (inputIndex / 2);
                input[inputIndex]--;
            }
            inputIndex++;
            while (input[inputIndex] > 0 && inputIndex < inputFillIndex && resultIndex < resultArray.length) {
                while (inputFillIndex >= 1 && input[inputFillIndex] == 0) {
                    inputFillIndex -= 2;
                }
                resultArray[resultIndex++] = (short) (inputFillIndex / 2);
                input[inputIndex]--;
                input[inputFillIndex]--;
            }
            inputIndex++;
        }
        long result = 0;
        for (int i = 0; i < resultArray.length; i++) {
            result += (long) resultArray[i] * i;
        }
        return result;
    }
}
