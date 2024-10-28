package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class task02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to generate permutations: ");
        String input = scanner.nextLine();

        System.out.print("Exclude duplicate permutations? (yes/no): ");
        boolean excludeDuplicates = scanner.nextLine().equalsIgnoreCase("yes");

        if (input.isEmpty()) {
            System.out.println("Input string is empty. Please provide a valid string.");
            return;
        }

        // Generate permutations using recursive approach
        List<String> recursivePermutations = generatePermutations(input, excludeDuplicates);
        System.out.println("Permutations (Recursive): " + recursivePermutations);

        // Generate permutations using iterative approach
        List<String> iterativePermutations = generatePermutationsIteratively(input, excludeDuplicates);
        System.out.println("Permutations (Iterative): " + iterativePermutations);
    }

    /**
     * Generates all permutations of the input string recursively.
     *
     * @param str The input string.
     * @param excludeDuplicates Whether to exclude duplicate permutations.
     * @return A list of permutations.
     */
    public static List<String> generatePermutations(String str, boolean excludeDuplicates) {
        List<String> permutations = new ArrayList<>();
        generatePermutationsHelper("", str, permutations, excludeDuplicates ? new HashSet<>() : null);
        return permutations;
    }

    /**
     * Helper method for generating permutations.
     *
     * @param prefix The current prefix for building permutations.
     * @param str    The remaining characters.
     * @param result The list to store the permutations.
     * @param seen   A set to track seen permutations (for duplicates).
     */
    private static void generatePermutationsHelper(String prefix, String str, List<String> result, HashSet<String> seen) {
        if (str.length() == 0) {
            if (seen == null || seen.add(prefix)) {
                result.add(prefix);
            }
        } else {
            for (int i = 0; i < str.length(); i++) {
                String newPrefix = prefix + str.charAt(i);
                String remaining = str.substring(0, i) + str.substring(i + 1);
                generatePermutationsHelper(newPrefix, remaining, result, seen);
            }
        }
    }

    /**
     * Generates all permutations of the input string iteratively.
     *
     * @param str The input string.
     * @param excludeDuplicates Whether to exclude duplicate permutations.
     * @return A list of permutations.
     */
    public static List<String> generatePermutationsIteratively(String str, boolean excludeDuplicates) {
        List<String> permutations = new ArrayList<>();
        HashSet<String> set = excludeDuplicates ? new HashSet<>() : null;

        // Start with the first character
        permutations.add(String.valueOf(str.charAt(0)));

        // Iterate through the remaining characters
        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            List<String> newPermutations = new ArrayList<>();

            for (String perm : permutations) {
                for (int j = 0; j <= perm.length(); j++) {
                    String newPerm = perm.substring(0, j) + currentChar + perm.substring(j);
                    if (set == null || set.add(newPerm)) {
                        newPermutations.add(newPerm);
                    }
                }
            }
            permutations = newPermutations;
        }
        return permutations;
    }
}
