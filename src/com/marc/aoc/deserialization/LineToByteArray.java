package com.marc.aoc.deserialization;

public class LineToByteArray implements Deserializer<byte[]> {

    @Override
    public byte[] deserialize(String puzzleInput) {
        byte[] array = new byte[puzzleInput.length()];
        for (int i = 0; i < puzzleInput.length(); i++) {
            array[i] = (byte) (puzzleInput.charAt(i) - '0');
        }
        return array;
    }
}
