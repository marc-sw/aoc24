package com.marc.aoc.day;

import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.LineToByteArray;

public class Day09 implements Day<byte[]> {

    private static int calculateOutputSize(byte[] input) {
        int size = 0;
        for (int i = 0; i < input.length; i += 2) {
            size += input[i];
        }
        return size;
    }

    @Override
    public int day() {
        return 9;
    }

    @Override
    public Deserializer<byte[]> deserializer() {
        return new LineToByteArray();
    }

    @Override
    public long partOne(byte[] input) {
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

    private static int sumTill(byte[] input, int index) {
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum += input[i];
        }
        return sum;
    }

    @Override
    public long partTwo(byte[] input) {
        byte[] spaceDistribution = input.clone();
        long result = 0;

        int start = input.length - 1;
        if (start % 2 != 0) {
            start--;
        }
        int spaceStart = 1;
        for (int i = start; i > 1; i -= 2) {
            boolean inserted = false;
            for (int j = spaceStart; j < i && !inserted; j += 2) {
                if (spaceDistribution[j] >= input[i]) {
                    int resultIndex = sumTill(spaceDistribution, j);
                    int size = input[i];
                    for (int k = resultIndex; k < resultIndex + size; k++) {
                        result += (long) k * (i / 2);
                    }
                    spaceDistribution[i] = 0;
                    spaceDistribution[j] -= (byte) size;
                    spaceDistribution[j - 1] += (byte) size;
                    inserted = true;
                }
            }
            if (!inserted) {
                int index = sumTill(spaceDistribution, i);
                for (int j = index; j < index + input[i]; j++) {
                    result += (long) j * (i / 2);
                }
            }
        }
        return result;
    }
}
