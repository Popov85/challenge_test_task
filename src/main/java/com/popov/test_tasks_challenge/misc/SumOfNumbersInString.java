package com.popov.test_tasks_challenge.misc;

import java.util.Arrays;

/**
 * A string is formed out of digits;
 * Calculate the sum of all digits it contains;
 * If it happens to contain a non-digit - just ignore it!
 */
public class SumOfNumbersInString {
    public static void main(String[] args) {
        int result = new SumOfNumbersInString().calcSum("A01020100!");
        System.out.println("Result = "+result);
    }

    public int calcSum(String str) {
        //(!) Split into an array od digits!
        String[] split = str.split("");
        int sum = Arrays.stream(split).mapToInt(s -> {
            try {
                Integer result = Integer.valueOf(s);
                return result;
            } catch (Exception e) {
                return 0;
            }
        }).sum();
        return sum;
    }
}
