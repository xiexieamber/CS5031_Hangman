package stacs;


import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent the state of the hangman game.
 * */
public class GameState {
    private String wordToGuess; //List of words to guess
    private StringBuilder currentState; // current state of the word
    private Set<Character> guessedLetters; // the letters that have been guessed
    private int wrongGuesses; // the number of wrong guesses made so far

    private static final int MAX_ATTEMPTS = 6; // the maximum number of wrong guesses allowed
    private int allGuesses; // the total number of guesses made so far (including wrong guesses)


    /**
     * Constructor for the GameState class.
     * @param wordToGuess
     */
    public GameState(String wordToGuess) {
        this.wordToGuess = wordToGuess.toLowerCase();
        this.currentState = new StringBuilder("_".repeat(wordToGuess.length()));
        this.guessedLetters = new HashSet<>();
        this.wrongGuesses = 0;
        this.allGuesses = 0;
    }

    /**
     * Get the total number of guesses made so far (including wrong guesses).
     * @return The total number of guesses made so far.
     */
    public int getAllGuesses() {
        return allGuesses;
    }

    /**
     * Get the hangman figure based on the number of wrong guesses.
     * @param wrongGuesses The number of wrong guesses made so far.
     * @return The hangman figure.
     */
    public static String getHangmanFigure(int wrongGuesses) {
        switch (wrongGuesses) {
            case 0:
                return  "  +---+\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "========\n";
            case 1:
                return  "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "========\n";
            case 2:
                return  "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "========\n";
            case 3:
                return  "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "========\n";
            case 4:
                return  "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "========\n";


            case 5:
                return  "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " /    |\n" +
                        "      |\n" +
                        "========\n";
            case 6:
                return  "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " / \\  |\n" +
                        "      |\n" +
                        "========\n";
            default:
                return "";
        }
    }



    /**
     * Process the user's guess.
     * @return True if the guess is correct, false if the guess is wrong.
     * */
    public boolean guess(char guess) {
        if (!Character.isLetter(guess)) {
            System.out.println("INVALID CHARACTER!");
            return false;
        }

        guess = Character.toLowerCase(guess);

        if (guessedLetters.contains(guess)) {
            System.out.println("ALREADY GUESSED");
            return false;
        }

        if (isGameOver() ) {
            System.out.println("GAME OVER!");
            return false;
        }

        guessedLetters.add(guess);

        if (wordToGuess.indexOf(guess) >= 0) {
            updateCurrentState(guess);
            System.out.println("CORRECT GUESS!");
            allGuesses++;
            return true;
        } else {
            wrongGuesses++;
            System.out.println("WRONG GUESS!");
            System.out.println(getHangmanFigure(wrongGuesses));
            allGuesses++;
            return false;
        }
    }

    /**
     * Update the current state of the word based on the user's guess.
     * @param guess The user's guess.
     */
    private void updateCurrentState(char guess) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                currentState.setCharAt(i , guess); // 注意：由于你在getCurrentState中对每个字符后都加了一个空格，所以这里索引需要乘以2
            }
        }
    }


    /**
     * Whether the game is over or not.
     * @return True if the game is over, false if the game is not over.
     */
    public boolean isGameOver() {
        return wrongGuesses >= MAX_ATTEMPTS || isWordGuessed();
    }

    /**
     * Whether the word has been guessed or not.
     * @return True if the word has been guessed, false if the word has not been guessed.
     */
    public boolean isWordGuessed() {
        for (char letter : wordToGuess.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the number of wrong guesses made so far.
     * @return The current word state.
     */
    public String getCurrentState() {
        StringBuilder display = new StringBuilder();
        for (char letter : wordToGuess.toCharArray()) {
            display.append(guessedLetters.contains(letter) ? letter : '_').append(' ');
        }
        currentState = display;
        return display.toString();
    }

    /**
     * Get the current word state.
     * @return The current word state.
     */
    public StringBuilder getCurrentStateBuilder() {
        return currentState;
    }

    /**
     * Get the word to guess.
     * @return The word to guess.
     */
    public String getWordToGuess() {
        return wordToGuess;
    }

    /**
     * Get the number of wrong guesses made so far.
     * @return The number of wrong guesses made so far.
     */
    public int getWrongGuesses() {
        return wrongGuesses;
    }

    /**
     *Get the length of the word to guess.
     * @return The length of the word to guess.
     */
    public int getWordLength() {
        return wordToGuess.length();
    }

    /**
     * Get the set of guessed letters.
     * @return The set of guessed letters.
     */
    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    /**
     * Update the current state of the word based on the user's guess.
     * @param currentState The user's guess.
     */
    public void updateCurrentState(char currentState,int index) {
        this.currentState.setCharAt(index, currentState);
    }
}
