package com.marc.aoc.day;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements Day {

    private String puzzleInput;

    private int multiplyCurrent(Matcher matcher) {
        String expression = matcher.group();
        int commaIndex = expression.indexOf(',');
        int i = Integer.parseInt(expression.substring(4, commaIndex));
        int j = Integer.parseInt(expression.substring(commaIndex + 1, expression.length() - 1));;
        return i * j;
    }

    @Override
    public void setup(String puzzleInput) {
        this.puzzleInput = puzzleInput;
    }

    @Override
    public int getNumber() {
        return 3;
    }

    @Override
    public int solvePartOne() {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Matcher matcher = pattern.matcher(puzzleInput);
        String expression;
        int i;
        int j;
        int commaIndex;
        int sum = 0;
        while (matcher.find()) {
            sum += multiplyCurrent(matcher);
        }
        return sum;
    }

    @Override
    public int solvePartTwo() {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Matcher matcher = pattern.matcher(puzzleInput);
        int doCache;
        int dontCache;
        int lastDo = 0;
        int lastDont = 0;
        int sum = 0;
        while (matcher.find()) {
            doCache = puzzleInput.indexOf("do()", lastDo + 1);
            while (doCache < matcher.start() && doCache != -1) {
                lastDo = doCache;
                doCache = puzzleInput.indexOf("do()", lastDo + 1);
            }

            dontCache = puzzleInput.indexOf("don't()", lastDont + 1);
            while (dontCache < matcher.start() && dontCache != -1) {
                lastDont = dontCache;
                dontCache = puzzleInput.indexOf("don't()", lastDont + 1);
            }

            if (lastDo < lastDont) {
                continue;
            }
            sum += multiplyCurrent(matcher);
        }
        return sum;
    }
}
