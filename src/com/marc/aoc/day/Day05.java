package com.marc.aoc.day;

import java.util.*;

public class Day05 implements Day {

    private List<List<Integer>> updates;
    private Map<Integer, Set<Integer>> pageRules;

    @Override
    public int getNumber() {
        return 5;
    }

    @Override
    public void setup(String puzzleInput) {
        String[] parts = puzzleInput.split(System.lineSeparator() + System.lineSeparator());
        String[] ruleLines = parts[0].split(System.lineSeparator());
        String[] ruleParts;
        int first;
        int second;
        pageRules = new HashMap<>();

        for (String line: ruleLines) {
            ruleParts = line.split("\\|", 2);
            first = Integer.parseInt(ruleParts[0]);
            second = Integer.parseInt(ruleParts[1]);
            if (!pageRules.containsKey(first)) {
                pageRules.put(first, new HashSet<>());
            }
            pageRules.get(first).add(second);

        }

        String[] updateLines = parts[1].split(System.lineSeparator());
        String[] updateParts;
        List<Integer> update;
        updates = new ArrayList<>(updateLines.length);
        for (String line: updateLines) {
            updateParts = line.split(",");
            update = new ArrayList<>(updateParts.length);
            for (String updatePart: updateParts) {
                update.add(Integer.parseInt(updatePart));
            }
            updates.add(update);
        }
    }

    private boolean isInOrder(List<Integer> update) {
        for (int i = 1; i < update.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (pageRules.containsKey(update.get(i)) && pageRules.get(update.get(i)).contains(update.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void orderUpdate(List<Integer> update) {
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
    public long solvePartOne() {
        int solution = 0;
        for (List<Integer> update: updates) {
            if (isInOrder(update)) {
                solution += update.get(update.size() / 2);
            }
        }
        return solution;
    }

    @Override
    public long solvePartTwo() {
        int solution = 0;
        for (List<Integer> update: updates) {
            if (!isInOrder(update)) {
                orderUpdate(update);
                solution += update.get(update.size() / 2);
            }
        }
        return solution;
    }
}
