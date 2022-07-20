package com.popov.test_tasks_challenge.task_3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Some numbers are formed with closed paths. The digits 0, 4, 6 and 9 each have
 * 1 closed path, and 8 has 2.
 * None of the other numbers is formed with a closed path.
 * Given a number, determine the total number of closed paths in all of its digits combined.
 *
 * Example:
 * number = 649578
 * The digits with closed paths are 6, 4, 9, and 9
 * The total number of closed paths = 1+1+1+2 = 5
 */
public class TestTask3 {

    private static Map<Integer, Integer> initConditions =
            Map.of(0, 1, 4, 1, 6, 1, 9, 1, 8, 2);

    public static void main(String[] args) {
        int result = closedPaths(649578);
        System.out.println("Result = "+result);
    }

    public static int closedPaths(int number) {
        // Write your code here
        String temp = Integer.toString(number);
        List<Integer> digits = IntStream.range(0, temp.length()).boxed()
                .map(i -> Integer.valueOf(temp.substring(i, i + 1))).collect(Collectors.toList());

        Integer count =
                digits.stream().filter(d -> initConditions.get(d) != null)
                        .reduce(0, (subtotal, element) -> subtotal + initConditions.get(element));
        return count;
    }
}
