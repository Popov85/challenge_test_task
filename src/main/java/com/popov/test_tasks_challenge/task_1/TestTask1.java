package com.popov.test_tasks_challenge.task_1;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TestTask1 {

    public static void main(String[] args) {
        int result = countPairs(List.of(1, 5, 9, 3, 7, 1, 2, 4, 5), 1);
        System.out.println("Result = "+result);
    }

    /*
     * Complete the 'countPairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY numbers
     *  2. INTEGER k
     */

    static class Pair {
        private Integer a;
        private Integer b;

        public Pair(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a.equals(pair.a) && b.equals(pair.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    public static int countPairs(List<Integer> numbers, int k) {
        // Write your code here
        Set<Pair> pairs = new HashSet<>();
        for (int i=0; i<numbers.size(); i++) {
            for (int j=i; j<numbers.size(); j++) {
                pairs.add(new Pair(numbers.get(i), numbers.get(j)));
            }
        }
        int counter = 0;
        for (Pair pair : pairs) {
            if (pair.getB() - pair.getA() == k) {
                counter ++;
            }
        }
        return counter;
    }

}
