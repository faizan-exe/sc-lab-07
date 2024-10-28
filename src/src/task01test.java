package src;

import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class task01test {

    private static String testDirectory;

    @BeforeAll
    public static void setup() throws IOException {
        // Create a temporary directory for testing
        testDirectory = Files.createTempDirectory("testDir").toString();
        // Create test files
        Files.createFile(Paths.get(testDirectory, "file1.txt"));
        Files.createFile(Paths.get(testDirectory, "file2.txt"));
        Files.createDirectory(Paths.get(testDirectory, "subDir"));
        Files.createFile(Paths.get(testDirectory, "subDir", "file3.txt"));
    }

    @AfterAll
    public static void teardown() throws IOException {
        // Delete the temporary test directory and its contents
        Files.walk(Paths.get(testDirectory))
                .sorted((a, b) -> b.compareTo(a)) // Delete files before directories
                .map(java.nio.file.Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void testFileFoundInRoot() {
        String[] args = { testDirectory, "file1.txt" };
        boolean found = task01.searchFile(new File(args[0]), args[1]);
        assertTrue(found, "File should be found in the root directory.");
    }

    @Test
    public void testFileFoundInSubDirectory() {
        String[] args = { testDirectory, "file3.txt" };
        boolean found = task01.searchFile(new File(args[0]), args[1]);
        assertTrue(found, "File should be found in the sub-directory.");
    }

    @Test
    public void testFileNotFound() {
        String[] args = { testDirectory, "non_existing_file.txt" };
        boolean found = task01.searchFile(new File(args[0]), args[1]);
        assertFalse(found, "File should not be found as it does not exist.");
    }

    @Test
    public void testInvalidDirectory() {
        String[] args = { "invalid/path", "file1.txt" };
        File directory = new File(args[0]);

        // Check that the directory does not exist
        assertFalse(directory.exists(), "Directory should not exist.");
    }
}
