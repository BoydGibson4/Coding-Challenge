// Boyd Gibson || 06/08/2024
// Sword Group || Short Programming Challenge
// Made and ran using replit

import java.io.BufferedReader; // Reading text from a character input stream
import java.io.FileReader; // Reading character files
import java.io.IOException; // Handle exceptions that may occur during I/O operations
import java.util.HashMap; // Storing character frequencies
import java.util.Map; // The Map interface
import java.util.PriorityQueue; // Sorting characters by frequency

public class Main {

  public static void main(String[] args) {
    if (args.length == 0) { // Check if filename is provided as an argument
      System.out.println("Usage: java Main <filename> [-i]");
      System.exit(1); // Exit if no filename is provided
    }

    String filename = args[0]; // Get the filename from arguments
    boolean caseInsensitive = false; // Default to case sensitive

    // Check for case insensitivity flag
    if (args.length > 1 && args[1].equals("-i")) {
      caseInsensitive = true; // Set to case insensitive if -i flag is present
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      Map<Character, Integer> frequencyMap = new HashMap<>(); // Map to store character frequencies
      int totalCharacters = 0; // Counter for total characters excluding whitespace
      String line;

      while ((line = reader.readLine()) != null) { // Read each line from the file
        for (char c : line.toCharArray()) { // Convert line to character array
          if (isIgnorableWhitespace(c)) { // Skip ignorable whitespace characters
            continue;
          }

          if (caseInsensitive) { // Convert character to lowercase if case insensitive
            c = Character.toLowerCase(c);
          }

          frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1); // Update character frequency
          totalCharacters++; // Increment total character count
        }
      }

      System.out.println("Total characters: " + totalCharacters); // Print total characters
      printTopCharacters(frequencyMap); // Print top 10 characters

    } catch (IOException e) { // Handle potential IO exceptions
      System.err.println("Error reading file: " + e.getMessage()); // Print error message
    }
  }

  // Method to check if a character is an ignorable whitespace
  private static boolean isIgnorableWhitespace(char c) {
    return c == ' ' || c == '\r' || c == '\n' || c == '\t';
  }

  // Method to print the top 10 most frequently occurring characters
  private static void printTopCharacters(Map<Character, Integer> frequencyMap) {
    PriorityQueue<Character> pq = new PriorityQueue<>(
        (a, b) -> frequencyMap.get(b) - frequencyMap.get(a)); // Priority queue to sort characters by frequency

    pq.addAll(frequencyMap.keySet()); // Add all characters to the priority queue

    System.out.println("Top 10 most frequently occurring characters:");
    int count = 0;
    while (!pq.isEmpty() && count < 10) { // Print top 10 characters
      char c = pq.poll();
      System.out.println(c + " (" + frequencyMap.get(c) + ")");
      count++;
    }
  }
}
