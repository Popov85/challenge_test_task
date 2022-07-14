package com.popov.test_tasks_challenge.task_1;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Given an integer k and a list of integers, count the number of distinct
 * valid pairs of integers (a, b) in the list for which a+k=b. Two pairs of integers(a, b)
 * and (c, d) are considered distinct if at least one element of (a, b)
 * does not also belong to (c, d). Note that the elements in a pair might be the same element
 * in the array. An instance of this is below where k = 0;
 *
 * Example #1:
 * n=4, k = 1
 * numbers = [1, 1, 1, 2]
 *
 * This array has three different valid pairs (1, 1), (1, 2) and (2, 2)
 * For k=1 there is only 1 valid pair which satisfies a+k=b the pair (1, 2)
 *
 * Example #2
 * n=2, k=0
 * numbers = [1, 2]
 *
 * There are three valid pairs (1, 1), (2, 2) and (1, 2).
 * For k= 0, two valid pairs satisfy a+k=b: 1+0 = 1, 2+0 = 2
 *
 */
public class TestTask1 {

    public static void main(String[] args) {
        int result = countPairs(List.of(1, 2), 0);
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
                pairs.add(new Pair(numbers.get(j), numbers.get(i)));
            }
        }
        long count =
                pairs.stream().filter(pair -> (pair.getA() + k == pair.getB())).count();
        return Long.valueOf(count).intValue();
    }
}
