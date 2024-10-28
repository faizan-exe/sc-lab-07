package src;

import java.io.File;

public class task01 {
    
    public static void main(String[] args) {
        // Check if the required arguments are provided
        if (args.length < 2) {
            System.out.println("Usage: java Task01 <directory-path> <file-name>");
            return;
        }

        String directoryPath = args[0];
        String fileName = args[1];

        // Start the search
        try {
            File directory = new File(directoryPath);
            if (!directory.exists() || !directory.isDirectory()) {
                System.out.println("The specified path is not a valid directory.");
                return;
            }
            
            boolean found = searchFile(directory, fileName);
            if (!found) {
                System.out.println("File '" + fileName + "' not found in the directory.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Recursively search for a file in the specified directory and its subdirectories.
     *
     * @param directory The directory to search.
     * @param fileName  The name of the file to search for.
     * @return true if the file is found, false otherwise.
     */
    public static boolean searchFile(File directory, String fileName) {
        File[] files = directory.listFiles();
        if (files == null) return false;


        for (File file : files) {
            if (file.isDirectory()) {
                // Recur into the sub-directory
                if (searchFile(file, fileName)) {
                    return true;
                }
            } 
            else if (file.getName().equals(fileName)) {
                System.out.println("Found: " + file.getAbsolutePath());
                return true;
            }
      
        }
        return false;
    }
}
