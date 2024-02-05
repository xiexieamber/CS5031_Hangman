package stacs;
import java.io.InputStream;
import java.util.*;

/*
Add
Refactor the code to make it more readable and understandable:
1. Increase readability and maintainability
Separate logic: Separate game logic, user input processing, and game interface display into separate methods to improve code clarity and maintainability.
2. Structural optimisation
Optimise guess methods: Adjust methods to avoid duplicating code and to handle correct guess and incorrect guess logic more clearly.


Add javadoc comments.
* */


public class Hangman {
    private static final int MAX_ATTEMPTS = 6; // 6 wrong guesses allowed
    private String wordToGuess; // the word to guess
    private Set<Character> guessedLetters; // the letters that have been guessed
    private int allGuesses; // the total number of guesses made so far (including wrong guesses)
    private int wrongGuesses; // the number of wrong guesses made so far

    /**
     * Constructor for the Hangman class.
     * */
    public Hangman(String wordToGuess) {
        this.wordToGuess = wordToGuess.toLowerCase();
        this.guessedLetters = new HashSet<>();
        this.allGuesses = 0;
        this.wrongGuesses = 0;
    }

    /**
     * Test whether the input is a valid letter or not, and whether it has been guessed before.
     * @return A valid letter.
     * */
    private char getUserInput(Scanner scanner) {
        String input;
        char guess;
        do {
            System.out.print("Please enter a letter: ");
            input = scanner.nextLine();
            guess = input.length() == 1 ? input.toLowerCase().charAt(0) : ' ';
            if (!Character.isLetter(guess)) {
                System.out.println("Please enter a valid letter!");
            } else if (guessedLetters.contains(guess)) {
                System.out.println("You have already guessed this letter!");
                guess = ' ';
            }
        } while (!Character.isLetter(guess));
        return guess;
    }


    /**
     * Process the user's guess.
     * @return True if the guess is correct, false if the guess is wrong.
     * */
    public boolean guess(char guess) {
        if (isGameOver()) {
            return false;
        }
        if (wordToGuess.indexOf(guess) >= 0) {
            guessedLetters.add(guess);
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
     * Test whether the game is over or not.
     * @return True if the game is over, false if the game is not over.
     * */
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
        return display.toString();
    }

    /**
     * Play the game, and ask the user if they want to play again.
     */
    private void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to hangman!");
        while (!isGameOver()) {
            System.out.println("The current state of the word: " + getCurrentState());
            char guess = getUserInput(scanner);
            guess(guess);
        }

        if (isWordGuessed()) {
            System.out.println("CONGRATULATIONS! THE WORD IS " + wordToGuess.toUpperCase());
        } else {
            System.out.println("THE GAME IS OVER. YOU LOST! THE WORD WAS " + wordToGuess.toUpperCase());
        }
        System.out.println("DO YOU WANT TO PLAY AGAIN? (Y/N)");
        String playAgain = scanner.nextLine();
        if (playAgain.equalsIgnoreCase("Y")) {
            startGame();
        } else {
            System.out.println("THANK YOU FOR PLAYING!");
            scanner.close();
            System.exit(0);
        }
    }

    /**
     * The function to get a random word from the file.to start the game.
     */
    public static void startGame() {
        String randomWord = getRandomWord();
        new Hangman(randomWord).playGame();
    }

    /**
     * The main method to start the game.
     */
    public static void main(String[] args) {
        startGame();
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
     * Get a random word from the file.
     * @return A random word.
     */
    private static String getRandomWord() {
        List<String> words = new ArrayList<>();
        InputStream inputStream = Hangman.class.getClassLoader().getResourceAsStream("wordlist.txt");
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine().trim();
            if (word.length() == 5) {
                words.add(word);
            }
        }
        scanner.close();
        if (words.isEmpty()) {
            System.out.println("THE WORD LIST IS EMPTY! PLEASE CHECK THE FILE.");
            return null;
        }
        String s = words.get(new Random().nextInt(words.size()));
//        return "apple";
        return  s;
    }

    /**
     * Get the total number of guesses made so far (including wrong guesses).
     * @return The total number of guesses made so far.
     */
    public int getAllGuesses() {
        return allGuesses;
    }

    /**
     * Get the number of wrong guesses made so far.
     * @return The number of wrong guesses made so far.
     */
    public int getWrongGuesses() {
        return wrongGuesses;
    }
}

