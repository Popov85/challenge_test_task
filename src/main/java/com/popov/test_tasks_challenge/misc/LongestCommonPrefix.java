package com.popov.test_tasks_challenge.misc;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given an array of strings;
 * Need to find the longest common prefix like
 * For an array of ["test", "test123", "test1"] the result would be "test".
 */
public class LongestCommonPrefix {
    private static final String DEFAULT_STRING="";

    public static void main(String[] args) {
        String result = new LongestCommonPrefix()
                .longestCommonPrefix(new String[]{"test", "test123", "test1"});
        System.out.println("Result = "+result);
    }

    public String longestCommonPrefix(String[] strings) {
        if (strings==null
                || strings.length==0
                || strings[0]==null) return DEFAULT_STRING;
        String first = strings[0];
        // Find all possible prefixes
        List<String> allPrefixes = IntStream.rangeClosed(1, first.length())
                .filter(i -> checkCommonPrefix(strings, first.substring(0, i)))
                .mapToObj(i -> first.substring(0, i)).collect(Collectors.toList());
        // Find longest prefix
        Optional<String> longestPrefix =
                allPrefixes.stream().max(Comparator.comparingInt(String::length));
        return longestPrefix.orElse(DEFAULT_STRING);
    }
    private boolean checkCommonPrefix(String[] strings, String prefix) {
        for (String string : strings) {
            if (string==null || !string.startsWith(prefix)) return false;
        }
        return true;
    }
}
