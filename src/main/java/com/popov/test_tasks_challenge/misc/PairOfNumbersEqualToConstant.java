package com.popov.test_tasks_challenge.misc;

import java.util.*;
import java.util.stream.IntStream;

/**
 * For an array of ints and given number of sum,
 * find such the pairs that in sum gives exactly sum!
 * Return these pairs.
 * @see <a href = "https://www.youtube.com/watch?v=IQ8FsvbvxVY&list=PLlsMRoVt5sTMMCwd_gLaaZMkQhzVh9hLA&index=17">Link</a>
 */
public class PairOfNumbersEqualToConstant {

    public static void main(String[] args) {
        Set<Pair> pairs =
                new PairOfNumbersEqualToConstant().getPair(new int[]{0, 4, 2, 3, 5, 1}, 3);
        System.out.println("Result = "+ pairs);
    }

    private static class Pair {
        private int a;
        private int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    public Set<Pair> getPair(int[] initArr, int sum) {
        Set<Integer> uniqueElements = new HashSet<>();
        Set<Pair> result = new HashSet<>();
        for (int i=0; i<initArr.length; i++) {
            uniqueElements.add(initArr[i]);
            if (uniqueElements.contains(sum - initArr[i])) {
                Pair pair = new Pair(initArr[i], sum - initArr[i]);
                result.add(pair);
            }
        }
        return result;
    }
}
