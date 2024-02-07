package stacs;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A class to generate random words.
 */
public class WordGenerator {
    private List<String> words; // List of words to choose from

    // Constructor
    public WordGenerator(String filePath) {
        this.words = loadWords(filePath);
    }

    /**
     * Load words from a file.
     * @param filePath
     * @return List of words
     */
    private List<String> loadWords(String filePath) {
        List<String> loadedWords = new ArrayList<>();
        InputStream inputStream = Hangman_new.class.getClassLoader().getResourceAsStream(filePath);
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if (!word.isEmpty()) {
                    loadedWords.add(word);
                }
            }
        }
        return loadedWords;
    }

    /**
     * Get a random word from the list of words.
     * @return Random word
     */
    public String getRandomWord() {
        if (words.isEmpty()) {
            throw new IllegalStateException("Word list is empty.");
        }
        return words.get(new Random().nextInt(words.size()));
    }
}
