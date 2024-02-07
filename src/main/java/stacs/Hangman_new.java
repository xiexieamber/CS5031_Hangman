package stacs;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Hangman game - version - 5.
 * Add
 * 1. Split Hangman into different classes with different responsibilities.
 * 2. Added a configuration file to store the configuration of the game(MAX_ATTMPT AND FILE_PATH).
 * 3. Added a test case for the Hangman_new class.
 */
public class Hangman_new {
    private GameState gameState; // The game state.
    private HintSystem hintSystem; // The hint system.
    private WordGenerator wordGenerator; // The word generator.
    private Config config; // The configuration.

    /**
     * Create a new hangman game.
     * @throws IOException If the word list file cannot be found.
     * */
    public Hangman_new() throws IOException {
        this.config = new Config();
        this.wordGenerator = new WordGenerator(config.getWordListPath());
        String wordToGuess = wordGenerator.getRandomWord();
        this.gameState = new GameState(wordToGuess);
        this.hintSystem = new HintSystem(gameState, config.getMaxAttempts());
    }

    /**
     * Create a new hangman game with a specific word to guess.
     * @param wordToGuess
     * @throws IOException
     */
    public  Hangman_new(String wordToGuess) throws IOException {
        this.config = new Config();
        this.wordGenerator = new WordGenerator(config.getWordListPath());
        this.gameState = new GameState(wordToGuess);
        this.hintSystem = new HintSystem(gameState, config.getMaxAttempts());
    }

    /**
     * Get the game state.
     * @return The game state.
     * */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Get the hint system.
     * @return The hint system.
     * */
    public HintSystem getHintSystem() {
        return hintSystem;
    }

    /**
     * Play the game, and ask the user if they want to play again.
     */
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        instruction();
        while (!gameState.isGameOver()) {
            System.out.println("The current state of the word: " + gameState.getCurrentState());
            char guess = getUserInput(scanner);
            if (guess != 0) {
                gameState.guess(guess);
            }
        }
        if (gameState.isWordGuessed()) {
            System.out.println("CONGRATULATIONS! THE WORD IS " + gameState.getWordToGuess().toUpperCase());
        } else {
            System.out.println("THE GAME IS OVER. YOU LOST! THE WORD WAS " + gameState.getWordToGuess().toUpperCase());
        }
        System.out.println("DO YOU WANT TO PLAY AGAIN? (Y/N)");
        String playAgain = scanner.nextLine();
        if (playAgain.equalsIgnoreCase("Y")) {
            String newWordToGuess = wordGenerator.getRandomWord();
            this.gameState = new GameState(newWordToGuess);
            playGame();
        } else {
            System.out.println("THANK YOU FOR PLAYING!");
            scanner.close();
            System.exit(0);
        }
    }

    /**
     * The function to display the instruction of the game.
     */
    private void instruction() {
        System.out.println("Welcome to hangman!");
        System.out.println("The word to guess has " + gameState.getWordLength() + " letters, and you have " +
                config.getMaxAttempts() + " wrong guesses allowed.");
        System.out.println("Don't worry, if you need help, you can use the hint. You have " +
                hintSystem.getRemainingHints() + " hints left.");
        System.out.println("Good Luck! :)");
    }


    /**
     * Test whether the input is a valid letter or not, and whether it has been guessed before.
     * @return A valid letter.
     * */
    private char getUserInput(Scanner scanner) {
        String input;
        char guess = ' ';
        do {
            System.out.print("Please enter a letter: ");
            input = scanner.nextLine();
            if (input.equals("hint")) {
                System.out.println(hintSystem.useHint());
                continue;
            }
            guess = input.length() == 1 ? input.toLowerCase().charAt(0) : ' ';
            if (!Character.isLetter(guess)) {
                System.out.println("Please enter a valid letter!");
            } else if (gameState.getGuessedLetters().contains(guess)) {
                System.out.println("You have already guessed this letter!");
                guess = ' ';
            }
        } while (!Character.isLetter(guess));
        return guess;
    }

    /**
     * The main function to start the game.
     * @param args
     */
    public static void main(String[] args) {
        try {
            new Hangman_new().playGame();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to start the game due to an error: " + e.getMessage());
        }
    }
}
