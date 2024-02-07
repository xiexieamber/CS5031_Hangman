import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.Hangman_new;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/*
Add
Hint test.
* */


class test_new {
    private Hangman_new game; // The game instance.

    /**
     * Set up the game instance before each test.
     * */
    @BeforeEach
    void setUp() {
        String firstWord = getFirstWordFromTestFile();
        try {
            game = new Hangman_new(firstWord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the first word from the test file.
     * @return The first word from the test file.
     * */
    private String getFirstWordFromTestFile() {
        try {
            InputStream inputStream = Hangman_new.class.getClassLoader().getResourceAsStream("wordlist-test.txt");
            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNextLine()) {
                return scanner.nextLine().trim();
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Test whether the hint is correct.
     */
    @Test
    void testUseHint() {
        game.getHintSystem().useHint();
        assertEquals("m _ _ _ _ ", game.getGameState().getCurrentState());
    }

    /**
     * Test whether the game is over after 6 wrong guesses.
     */
    @Test
    void testNoGuessesAfterGameOver() {
            game.getGameState().guess('z');
            game.getGameState().guess('x');
            game.getGameState().guess('c');
            game.getGameState().guess('k');
                game.getGameState().guess('b');
                game.getGameState().guess('j');

        assertFalse(game.getGameState().guess('m'));
        assertEquals(6, game.getGameState().getAllGuesses());
    }

    /**
     * Test wrong guesses be input twice won't increase the number of wrong guesses.
     */
    @Test
    void testGetWrongGuessesEnterRightWordTwice() {
        game.getGameState().guess('a');
        game.getGameState().guess('a');
        assertEquals(0, game.getGameState().getWrongGuesses());
    }

    /**
     * Test correct guesses be input twice will increase the number of correct guesses.
     */
    @Test
    void testGetAllGuessesEnterRightWordTwice() {
        game.getGameState().guess('a');
        game.getGameState().guess('a');
        assertEquals(1, game.getGameState().getAllGuesses());
    }

    /**
     * Test whether the hangman figure is correct.
     */
    @Test
    void testHangmanFigure() {
        String manFirstImage = game.getGameState().getHangmanFigure(0);
        assertEquals("  +---+\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "========\n", manFirstImage);
        String manSecondImage = game.getGameState().getHangmanFigure(1);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "========\n", manSecondImage);
        String manThirdImage = game.getGameState().getHangmanFigure(2);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                "  |   |\n" +
                "      |\n" +
                "      |\n" +
                "========\n", manThirdImage);
        String manFourthImage = game.getGameState().getHangmanFigure(3);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|   |\n" +
                "      |\n" +
                "      |\n" +
                "========\n", manFourthImage);

        String manFifthImage = game.getGameState().getHangmanFigure(4);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                "      |\n" +
                "      |\n" +
                "========\n", manFifthImage);

        String manSixthImage = game.getGameState().getHangmanFigure(5);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                " /    |\n" +
                "      |\n" +
                "========\n", manSixthImage);
        String manSeventhImage = game.getGameState().getHangmanFigure(6);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                " / \\  |\n" +
                "      |\n" +
                "========\n", manSeventhImage);
    }

    /**
     * Test when input a correct letter, the letter will be shown in the current state.
     */
    @Test
    void testGuessCorrectLetter() {
        assertTrue(game.getGameState().guess('a'));
        assertEquals("_ a _ _ _ ", game.getGameState().getCurrentState());
    }

    /**
     * Test when input a wrong letter, the number of wrong guesses and all guesses will increase.
     */
    @Test
    void testGuessWrongLetter() {
        assertFalse(game.getGameState().guess('z'));
        assertEquals(1, game.getGameState().getAllGuesses());
        assertEquals(1, game.getGameState().getWrongGuesses());
    }


    /**
     * Test whether the word is guessed.
     */
    @Test
    void testIsWordGuessed() {
        for (char c : "maven".toCharArray()) {
            game.getGameState().guess(c);
        }
        assertTrue(game.getGameState().isWordGuessed());
    }

    /**
     * Test whether the current state is correct when input two correct letters.
     */
    @Test
    void testGetCurrentState() {
        game.getGameState().guess('m');
        game.getGameState().guess('a');
        assertEquals("m a _ _ _ ", game.getGameState().getCurrentState());
    }

    /**
     * Test input invalid character.
     */
    @Test
    void testInputInvalidCharacter() {
        assertFalse(game.getGameState().guess('1'));
        assertFalse(game.getGameState().guess('?'));
        assertFalse(game.getGameState().guess('!'));
        assertFalse(game.getGameState().guess(' '));
    }
}
