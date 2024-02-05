package stacs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/*
Add
test the input condition, the input must be a letter, and cannot be a number, and cannot be a letter that has been guessed
add the option to play again
modify the input error message, it is invalid input, it does not affect the number of errors
* */


public class Hangman {
    private static final int MAX_ATTEMPTS = 6;
    private String wordToGuess;
    private Set<Character> guessedLetters;
    private int allGuesses;
    private int  wrongGuesses;

    public int getAllGuesses() {
        return allGuesses;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public Hangman(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.guessedLetters = new HashSet<>();
        this.allGuesses = 0;
        this.wrongGuesses = 0;
    }

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


    public boolean guess(char letter) {
        if (isGameOver()) {
            return false;
        }
        if (wordToGuess.indexOf(letter) >= 0 || wordToGuess.indexOf(Character.toLowerCase(letter)) >= 0) {
            guessedLetters.add(Character.toLowerCase(letter));
            allGuesses++;
            return true;
        } else {
            allGuesses++;
            wrongGuesses++;
            return false;
        }
    }

    public boolean isGameOver() {
        return wrongGuesses >= MAX_ATTEMPTS || isWordGuessed();
    }

    public boolean isWordGuessed() {
        for (char letter : wordToGuess.toCharArray()) {
            if (!guessedLetters.contains(letter) && !guessedLetters.contains(Character.toLowerCase(letter))) {
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
            System.out.println("The current state of the word " + game.getCurrentState());
            System.out.print("Please enter a letter: ");
            String  input = scanner.nextLine();
            if (input.length() != 1) {
                System.out.println("Please enter a valid letter instead of more than one letter!");
                continue;
            }
            char guess = input.charAt(0);
            if (guess < 'A' || (guess > 'Z' && guess < 'a') || guess > 'z') {
                System.out.println("Please enter a valid letter!");
                continue;
            } else if (game.guessedLetters.contains(guess) || game.guessedLetters.contains(Character.toLowerCase(guess))) {
                System.out.println("You have already guessed this letter!");
                continue;
            }
            if (game.guess(guess)) {
                System.out.println("CORRECT GUESS!");
            } else {
                String man = getHangmanFigure(game.wrongGuesses);
                System.out.println(man);
                System.out.println("WRONG GUESS!");
            }
        }
        if (game.isWordGuessed()) {
            System.out.println("CONGRATULATIONS! THE WORD IS " + randomWord);
        } else {
            System.out.println("THE GAME IS OVER. YOU LOST! THE WORD IS " + randomWord);
        }
        System.out.println("DO YOU WANT TO PLAY AGAIN? (Y/N)");
        String playAgain = scanner.nextLine();
        if (playAgain.equalsIgnoreCase("Y")) {
            main(args);
        } else {
            System.out.println("THANK YOU FOR PLAYING!");
            scanner.close();
            System.exit(0);
        }
    }
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
        return "apple";
//        return  s;
    }
}

