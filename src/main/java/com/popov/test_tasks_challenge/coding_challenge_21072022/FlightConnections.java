package com.popov.test_tasks_challenge.coding_challenge_21072022;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * In an effort to connect all airports with min costs, an airport authority needs to identify
 * the min number of new flight connections so that it is possible to reach all airports from
 * every other airport directly or indirectly.
 * Airports and flight connections are represented by a matrix.
 * Airports are represented by numbers 0, 1, 2 and so on.
 * So if airport 0 is connected via flight with airport 1 then
 * elements [0][1] and [1][0] will have the value true or false otherwise;
 * All flights are bi-directional;
 *
 * The function getMinimumConnections accepts a two-dimensional array matrix.
 * The function should return the minimum of flight connections
 * that need to be added so that all airports are reachable form other airport
 * directly or indirectly;
 *
 * For example the following code should print 1 as minimum
 * of 1 additional flight connection will connect all airports
 * directly or indirectly;
 *
 */
public class FlightConnections {

    public static void main(String[] args) {
        // Case two separate graphs: [0-1-4] && [2-3]
        boolean[][] matrix = new boolean[][] {
                {false, true, false, false, true},
                {true, false, false, false, false},
                {false, false, false, true, false},
                {false, false, true, false, false},
                {true, false, false, false, false}
        };
        System.out.println(getMinimumConnections(matrix)); // should print 1
    }
    public static int getMinimumConnections(boolean[][] matrix) {
        Set<Set<Integer>> separateGroups =
                findSeparateGroups(findConnections(matrix));
        // Number of connections required is less than separate groups by 1!
        return separateGroups.size()-1;
    }

    // Prepare a set of connected pairs, like
    // [[0-1], [0-4], [2, 3]]
    private static Set<Set<Integer>> findConnections(boolean[][] matrix) {
        // Row=Airport number
        // Find all rows that have [r][c]=true and [c][r]=true
        Set<Set<Integer>> result = new HashSet<>();
        for (int row=0; row < matrix.length; row++) {
            for (int col=0; col < matrix[row].length; col++) {
                boolean value = matrix[row][col];
                boolean reverted = matrix[col][row];
                if (value && reverted) {
                    result.add(new HashSet<>(Arrays.asList(row, col)));
                }
            }
        }
        System.out.println("All connections = "+result);
        return result;
    }

    // Prepare a set of separate groups
    // like  [[0-1-4], [2, 3]]
    private static Set<Set<Integer>> findSeparateGroups(Set<Set<Integer>> pairs) {
        Set<Set<Integer>> separateGroups = new HashSet<>();
        for (Set<Integer> nextPair : pairs) {
            // If any element from next pair participates in any of the existing groups - join them
            Optional<Set<Integer>> firstMatch = separateGroups.stream()
                    .filter(separateGroup ->
                            separateGroupContainsPair(separateGroup, nextPair)).findFirst();
            if (firstMatch.isPresent()) {
                // Join existing group and next pair
                firstMatch.get().addAll(nextPair);
            } else {// Create another group
                separateGroups.add(nextPair);
            }
        }
        System.out.println("All separate groups = "+separateGroups);
        return separateGroups;
    }

    private static boolean separateGroupContainsPair(Set<Integer> separateGroup, Set<Integer> pair) {
        return pair.stream().anyMatch(pairInt -> separateGroup.contains(pairInt));
    }
}
