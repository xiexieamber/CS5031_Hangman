package stacs;

import java.util.Random;
import java.util.Set;

/**
 * A class that provides hints to the user.
 */
public class HintSystem {
    private GameState gameState; // The game state
    private int hintCount; // The maximum number of hints allowed
    private Set<Character> hintedLetters; // The letters that have been hinted

    private int hint_count = 1; // The number of hints used


    /**
     * Create a new hint system.
     * @param gameState The game state.
     * @param maxHints The maximum number of hints allowed.
     * */
    public HintSystem(GameState gameState, int maxHints) {
        this.gameState = gameState;
        this.hintCount = maxHints;
        this.hintedLetters = gameState.getGuessedLetters();
        this.hint_count = 1;
    }

    /**
     * Use a hint.
     * @return A hint to the user.
     * */
    public String useHint() {
        if (hint_count > 0) {
            hint_count--;
            return provideHint();
        }else {
            return "Sorry, you have used all the hints";
        }
    }


    /**
     * Provide a hint to the user.
     * @return A hint to the user.
     * */
    public String provideHint() {
        Random random = new Random();
        char hintChar = 0;
        int index = -1;
        for (int i = 0; i < gameState.getWordToGuess().length(); i++) {
            char c = gameState.getWordToGuess().charAt(i);
            if (gameState.getCurrentState().charAt(i) == '_' && !hintedLetters.contains(c)) {
                hintChar = c;
                index = i;
                break;
            }
        }
        if (hintChar != 0) {
            hintedLetters.add(hintChar);
            gameState.getCurrentStateBuilder().setCharAt(index, hintChar);
            gameState.getGuessedLetters().add(hintChar);
            return "The letter '" + hintChar + "' is at position " + (index + 1) + " hint has been used, you luck :)" +
                    "\n" + "The current state of the word: " + gameState.getCurrentState();
        } else {
            return "Sorry, no more hints available.";
        }
    }


    /**
     * Get the number of remaining hints.
     * @return The number of remaining hints.
     * */
    public int getRemainingHints() {
        return hintCount;
    }
}
