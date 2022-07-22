package com.popov.test_tasks_challenge.misc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * For an array of integers that contains duplicates,
 * Find an element that appears in the array most frequently,
 * (!) If multiple elements appear in the same frequency, return the highest!
 */
public class MostFrequentNumber {

    private static final int DEFAULT_ANSWER=0;

    public static void main(String[] args) {
        int answer = new MostFrequentNumber()
                .findMostFrequent(new int[]{1, 1, 3, 2, 5, 4, 1, 3, 2, 7, 1, 2, 2});
        System.out.println("Answer = "+answer);
    }
    public int findMostFrequent(int[] numbers) {
        if (numbers==null || numbers.length==0) return DEFAULT_ANSWER;
        // Grouping all elements by frequency
        Map<Integer, Long> countedMap = Arrays.stream(numbers).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // Find max frequency
        Optional<Long> max =
                countedMap.values().stream().max(Comparator.naturalOrder());
        Long maxFrequency =
                max.orElseThrow(() -> new RuntimeException("Max not found!"));
        // Check if multiple keys, select the greatest!
        Optional<Map.Entry<Integer, Long>> result = countedMap.entrySet().stream()
                .filter(e -> e.getValue().equals(maxFrequency)).max(Comparator.comparing(Map.Entry::getKey));
        System.out.println("Entry = "+result);
        return result.isPresent() ? result.get().getKey() : DEFAULT_ANSWER;
    }
}
