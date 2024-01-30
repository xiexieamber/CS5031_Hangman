package stacs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {
    private static final int MAX_ATTEMPTS = 6;
    private String wordToGuess;
    private Set<Character> guessedLetters;
    private int wrongGuesses;

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public Hangman(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.guessedLetters = new HashSet<>();
        this.wrongGuesses = 0;
    }

    public boolean guess(char letter) {
        if (wordToGuess.indexOf(letter) >= 0) {
            guessedLetters.add(letter);
            wrongGuesses ++;
            return true;
        } else {
            wrongGuesses++;
            return false;
        }
    }

    public boolean isGameOver() {
        return wrongGuesses >= MAX_ATTEMPTS || isWordGuessed();
    }

    public boolean isWordGuessed() {
        for (char letter : wordToGuess.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    public String getCurrentState() {
        StringBuilder display = new StringBuilder();
        for (char letter : wordToGuess.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                display.append(letter);
            } else {
                display.append('_');
            }
            display.append(' ');
        }
        return display.toString();
    }

    public static void main(String[] args) {
        String randomWord = getRandomWord();
        Hangman game = new Hangman(randomWord);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to hangman!");
        while (!game.isGameOver()) {
            System.out.println("The current state of the word" + game.getCurrentState());
            System.out.print("Please enter a letter: ");
            char guess = scanner.nextLine().charAt(0);
            if (game.guess(guess)) {
                System.out.println("CORRECT GUESS!");
            } else {
                System.out.println("WRONG GUESS!");
            }
        }

        if (game.isWordGuessed()) {
            System.out.println("CONGRATULATIONS! THE WORD IS " + randomWord);
        } else {
            System.out.println("THE GAME IS OVER. YOU LOST! THE WORD IS " + randomWord);
        }
    }

    private static String getRandomWord() {
        List<String> words = new ArrayList<>();
        try {
            File file = new File("/Users/amber/Desktop/semaster2/CS5031/Hangman/src/main/resources/wordlist.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if (word.length() == 5) {
                    words.add(word);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("CAN'T FIND THE FILE: " + e.getMessage());
            return null;
        }
        if (words.isEmpty()) {
            System.out.println("THE WORD LIST IS EMPTY! PLEASE CHECK THE FILE.");
            return null;
        }
        String s = words.get(new Random().nextInt(words.size()));
        return s;
//        return "hello";
    }
}

