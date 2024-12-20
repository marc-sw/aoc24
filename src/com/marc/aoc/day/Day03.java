package com.marc.aoc.day;

import com.marc.aoc.deserializer.Deserializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements Day<String> {

    private int multiplyCurrent(Matcher matcher) {
        String expression = matcher.group();
        int commaIndex = expression.indexOf(',');
        int i = Integer.parseInt(expression.substring(4, commaIndex));
        int j = Integer.parseInt(expression.substring(commaIndex + 1, expression.length() - 1));;
        return i * j;
    }

    @Override
    public int day() {
        return 3;
    }

    @Override
    public Deserializer<String> deserializer() {
        return input -> input;
    }

    @Override
    public long partOne(String input) {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Matcher matcher = pattern.matcher(input);
        int sum = 0;
        while (matcher.find()) {
            sum += multiplyCurrent(matcher);
        }
        return sum;
    }

    @Override
    public long partTwo(String input) {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Matcher matcher = pattern.matcher(input);
        int doCache;
        int dontCache;
        int lastDo = 0;
        int lastDont = 0;
        int sum = 0;
        while (matcher.find()) {
            doCache = input.indexOf("do()", lastDo + 1);
            while (doCache < matcher.start() && doCache != -1) {
                lastDo = doCache;
                doCache = input.indexOf("do()", lastDo + 1);
            }

            dontCache = input.indexOf("don't()", lastDont + 1);
            while (dontCache < matcher.start() && dontCache != -1) {
                lastDont = dontCache;
                dontCache = input.indexOf("don't()", lastDont + 1);
            }

            if (lastDo < lastDont) {
                continue;
            }
            sum += multiplyCurrent(matcher);
        }
        return sum;
    }
}
