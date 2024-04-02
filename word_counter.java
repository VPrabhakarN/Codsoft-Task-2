import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class word_counter {
    private static final Set<String> STOP_WORDS = new HashSet<>(); // Set to store common stop words

    static {
        // Add common English stop words to the set
        STOP_WORDS.add("a");
        STOP_WORDS.add("an");
        STOP_WORDS.add("the");
        // Add more stop words as needed
    }

    public static void main(String[] args) {
        // Prompt the user for input
        System.out.println("Enter some text or provide a file path:");

        try {
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            String input = reader.readLine();

            String text;
            if (input.endsWith(".txt")) {
                // If the input ends with ".txt", treat it as a file path
                text = readFile(input);
            } else {
                // Otherwise, treat it as text input
                text = input;
            }

            // Split the text into words using space or punctuation as delimiters
            String[] words = text.split("\\s+|\\p{Punct}");

            // Initialize variables
            int totalWords = 0;
            Map<String, Integer> wordFrequency = new HashMap<>();

            // Iterate through the words and count them
            for (String word : words) {
                // Ignore empty strings and stop words
                if (!word.isEmpty() && !STOP_WORDS.contains(word.toLowerCase())) {
                    totalWords++;
                    // Update word frequency
                    wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
                }
            }

            // Display total count of words
            System.out.println("Total words: " + totalWords);

            // Display statistics
            System.out.println("Number of unique words: " + wordFrequency.size());
            System.out.println("Word frequency:");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }

    // Method to read file contents
    private static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
}
