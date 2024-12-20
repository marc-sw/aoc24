package com.marc.aoc.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AppearanceCounter {

    public static Map<Character, Set<Spot>> countLetterAppearances(char[][] data) {
        Map<Character, Set<Spot>> antennaMap = new HashMap<>();
        for (int row = 0; row < data.length; row++) {
            for (int column = 0; column < data[row].length; column++) {
                char c = data[row][column];
                if (!Character.isLetter(c)) {
                    continue;
                }
                if (!antennaMap.containsKey(c)) {
                    antennaMap.put(c, new HashSet<>());
                }
                antennaMap.get(c).add(new Spot(row, column));
            }
        }
        return antennaMap;
    }
}
