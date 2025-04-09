package util;

import java.io.*;

public class FileReaderUtil {
    public static void readPetsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Pet Info: " + line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading the file - " + e.getMessage());
        }
    }
}
