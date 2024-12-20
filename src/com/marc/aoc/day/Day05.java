package com.marc.aoc.day;

import com.marc.aoc.common.Pair;
import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.LinesToPairListListIntAndMapIntSetInt;

import java.util.*;

public class Day05 implements Day<Pair<List<List<Integer>>, Map<Integer, Set<Integer>>>> {

    private boolean isInOrder(List<Integer> update, Map<Integer, Set<Integer>> pageRules) {
        for (int i = 1; i < update.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (pageRules.containsKey(update.get(i)) && pageRules.get(update.get(i)).contains(update.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void orderUpdate(List<Integer> update, Map<Integer, Set<Integer>> pageRules) {
        int i = 0;
        int swapIndex;
        Integer current;
        Integer future = null;

        while (i < update.size() - 1) {
            current = update.get(i);
            swapIndex = -1;
            for (int j = i + 1; j < update.size() && swapIndex == -1; j++) {
                future = update.get(j);
                if (pageRules.containsKey(future) && pageRules.get(future).contains(current)) {
                    swapIndex = j;
                }
            }

            if (swapIndex != -1) {
                update.set(i, future);
                update.set(swapIndex, current);
            } else {
                i++;
            }
        }

    }

    @Override
    public int day() {
        return 5;
    }

    @Override
    public Deserializer<Pair<List<List<Integer>>, Map<Integer, Set<Integer>>>> deserializer() {
        return new LinesToPairListListIntAndMapIntSetInt();
    }

    @Override
    public long partOne(Pair<List<List<Integer>>, Map<Integer, Set<Integer>>> input) {
        int solution = 0;
        for (List<Integer> update: input.first) {
            if (isInOrder(update, input.second)) {
                solution += update.get(update.size() / 2);
            }
        }
        return solution;
    }

    @Override
    public long partTwo(Pair<List<List<Integer>>, Map<Integer, Set<Integer>>> input) {
        int solution = 0;
        for (List<Integer> update: input.first) {
            if (!isInOrder(update, input.second)) {
                orderUpdate(update, input.second);
                solution += update.get(update.size() / 2);
            }
        }
        return solution;
    }
}
