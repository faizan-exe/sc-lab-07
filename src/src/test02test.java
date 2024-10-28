package src;

import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class test02test {

    @Test
    public void testPermutationsWithDistinctCharacters() {
        String input = "abc";
        List<String> result = task02.generatePermutations(input, false);
        List<String> expected = List.of("abc", "acb", "bac", "bca", "cab", "cba");

        assertEquals(expected.size(), result.size(), "The number of permutations should match.");
        assertTrue(result.containsAll(expected), "Result should contain all expected permutations.");
    }

    @Test
    public void testPermutationsWithDuplicateCharacters() {
        String input = "aab";
        List<String> result = task02.generatePermutations(input, true);
        List<String> expected = List.of("aab", "aba", "baa");

        assertEquals(expected.size(), result.size(), "The number of permutations should match.");
        assertTrue(result.containsAll(expected), "Result should contain all expected permutations.");
    }

   
    @Test
    public void testSingleCharacter() {
        String input = "x";
        List<String> result = task02.generatePermutations(input, false);
        List<String> expected = List.of("x");

        assertEquals(expected.size(), result.size(), "The number of permutations should match.");
        assertTrue(result.containsAll(expected), "Result should contain the expected permutation.");
    }

 
    @Test
    public void testIterativePermutationsWithDuplicateCharacters() {
        String input = "aab";
        List<String> result = task02.generatePermutationsIteratively(input, true); // Exclude duplicates
        List<String> expected = List.of("aab", "aba", "baa");

        assertEquals(expected.size(), result.size(), "The number of permutations should match.");
        assertTrue(result.containsAll(expected), "Result should contain all expected permutations.");
    }
}
