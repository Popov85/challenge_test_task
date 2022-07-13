package com.popov.test_tasks_challenge.task_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTask3 {

    private static Map<Integer, Integer> initConditions = new HashMap<>();

    static {
        initConditions.put(0, 1);
        initConditions.put(4, 1);
        initConditions.put(6, 1);
        initConditions.put(8, 2);
    }

    public static void main(String[] args) {
        int result = closedPaths(369532018);
        System.out.println("Result = "+result);
    }

    public static int closedPaths(int number) {
        // Write your code here
        String temp = Integer.toString(number);
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < temp.length(); i++) {
            digits.add(Integer.valueOf(temp.substring(i, i+1)));
        }
        int count = 0;
        for (Integer digit : digits) {
            if (initConditions.get(digit)!=null) {
                count = count+initConditions.get(digit);
            }
        }
        return count;
    }
}
