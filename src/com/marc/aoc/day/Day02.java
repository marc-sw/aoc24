package com.marc.aoc.day;

import com.marc.aoc.deserializer.Deserializer;
import com.marc.aoc.deserializer.LinesToListListInt;

import java.util.ArrayList;
import java.util.List;

public class Day02 implements Day<List<List<Integer>>> {

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
    public int day() {
        return 2;
    }

    @Override
    public Deserializer<List<List<Integer>>> deserializer() {
        return new LinesToListListInt(" ");
    }

    @Override
    public long partOne(List<List<Integer>> input) {
        int saveReportsCount = 0;
        for (List<Integer> report: input) {
            if (isSaveReport(report)) {
                saveReportsCount++;
            }
        }
        return saveReportsCount;
    }

    @Override
    public long partTwo(List<List<Integer>> input) {
        int saveReportsCount = 0;
        for (List<Integer> report: input) {
            if (isSaveReport(report)) {
                saveReportsCount++;
                continue;
            }
            boolean save = false;
            for (int i = 0; i < report.size() && !save; i++) {
                List<Integer> copiedReport = new ArrayList<>(report);
                copiedReport.remove(i);
                if (isSaveReport(copiedReport)) {
                    saveReportsCount++;
                    save = true;
                }
            }
        }
        return saveReportsCount;
    }
}
