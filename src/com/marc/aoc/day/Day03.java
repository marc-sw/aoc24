package com.marc.aoc.day;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Day {

    private String memoryInput;

    public Day03(boolean test) {
        super(3, test);
    }

    @Override
    protected void setupTextInput(String textInput) {
        this.memoryInput = textInput;
    }

    @Override
    protected int solvePartOne() {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Matcher matcher = pattern.matcher(memoryInput);
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

    private int multiplyCurrent(Matcher matcher) {
        String expression = matcher.group();
        int commaIndex = expression.indexOf(',');
        int i = Integer.parseInt(expression.substring(4, commaIndex));
        int j = Integer.parseInt(expression.substring(commaIndex + 1, expression.length() - 1));;
        return i * j;
    }

    @Override
    protected int solvePartTwo() {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Matcher matcher = pattern.matcher(memoryInput);
        int doCache;
        int dontCache;
        int lastDo = 0;
        int lastDont = 0;
        int sum = 0;
        while (matcher.find()) {
            doCache = memoryInput.indexOf("do()", lastDo + 1);
            while (doCache < matcher.start() && doCache != -1) {
                lastDo = doCache;
                doCache = memoryInput.indexOf("do()", lastDo + 1);
            }

            dontCache = memoryInput.indexOf("don't()", lastDont + 1);
            while (dontCache < matcher.start() && dontCache != -1) {
                lastDont = dontCache;
                dontCache = memoryInput.indexOf("don't()", lastDont + 1);
            }

            if (lastDo < lastDont) {
                continue;
            }
            sum += multiplyCurrent(matcher);
        }
        return sum;
    }
}
