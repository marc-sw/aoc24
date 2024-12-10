package com.marc.aoc.day;

import java.util.ArrayList;
import java.util.List;

public class Day02 implements Day {

    private List<List<Integer>> reports;

    private boolean isSaveReport(List<Integer> report) {
        int offset = report.get(1) - report.get(0);
        if (offset == 0) {
            return false;
        }
        int min;
        int max;
        if (offset > 0) {
            min = 1;
            max = 3;
        } else {
            min = -3;
            max = -1;
        }
        for (int i = 1; i < report.size(); i++) {
            offset = report.get(i) - report.get(i - 1);
            if (offset > max || offset < min) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getNumber() {
        return 2;
    }

    @Override
    public void setup(String textInput) {
        String[] lines = textInput.split(System.lineSeparator());
        String[] lineParts;
        reports = new ArrayList<>(lines.length);
        List<Integer> report;
        for (String line : lines) {
            lineParts = line.split(" ");
            report = new ArrayList<>(lineParts.length);
            for (String linePart : lineParts) {
                report.add(Integer.parseInt(linePart));
            }
            reports.add(report);
        }
    }

    @Override
    public long solvePartOne() {
        int saveReportsCount = 0;
        for (List<Integer> report: reports) {
            if (isSaveReport(report)) {
                saveReportsCount++;
            }
        }
        return saveReportsCount;
    }

    @Override
    public long solvePartTwo() {
        int saveReportsCount = 0;
        for (List<Integer> report: reports) {
            if (isSaveReport(report)) {
                saveReportsCount++;
                continue;
            }
            for (int i = 0; i < report.size(); i++) {
                List<Integer> copiedReport = new ArrayList<>(report);
                copiedReport.remove(i);
                if (isSaveReport(copiedReport)) {
                    saveReportsCount++;
                    break;
                }
            }
        }
        return saveReportsCount;
    }
}
