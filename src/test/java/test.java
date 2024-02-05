import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.Hangman;
import java.io.InputStream;
import java.util.Scanner;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static stacs.Hangman.getHangmanFigure;

/*
Add
Refactor the code to make it more readable and easier to understand.
Add javadoc comments.
* */
class test {
    private Hangman game; // The game instance.

    /**
     * Set up the game instance before each test.
     * */
    @BeforeEach
    void setUp() {
        String firstWord = getFirstWordFromTestFile();
        game = new Hangman(firstWord);
    }

    /**
     * Get the first word from the test file.
     * @return The first word from the test file.
     * */
    private String getFirstWordFromTestFile() {
        try {
            InputStream inputStream = Hangman.class.getClassLoader().getResourceAsStream("wordlist-test.txt");
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
     * Test whether the game is over after 6 wrong guesses.
     */
    @Test
    void testNoGuessesAfterGameOver() {
        for (int i = 0; i < 6; i++) {
            game.guess('z');
        }
        assertFalse(game.guess('m'));
        assertEquals(6, game.getAllGuesses());
    }

    /**
     * Test wrong guesses be input twice won't increase the number of wrong guesses.
     */
    @Test
    void testGetWrongGuessesEnterRightWordTwice() {
        game.guess('a');
        game.guess('a');
        assertEquals(0, game.getWrongGuesses());
    }

    /**
     * Test correct guesses be input twice will increase the number of correct guesses.
     */
    @Test
    void testGetAllGuessesEnterRightWordTwice() {
        game.guess('a');
        game.guess('a');
        assertEquals(2, game.getAllGuesses());
    }

    /**
     * Test whether the hangman figure is correct.
     */
    @Test
    void testHangmanFigure() {
        String manFirstImage = getHangmanFigure(0);
        assertEquals("  +---+\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "========\n", manFirstImage);
        String manSecondImage = getHangmanFigure(1);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "========\n", manSecondImage);
        String manThirdImage = getHangmanFigure(2);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                "  |   |\n" +
                "      |\n" +
                "      |\n" +
                "========\n", manThirdImage);
        String manFourthImage = getHangmanFigure(3);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|   |\n" +
                "      |\n" +
                "      |\n" +
                "========\n", manFourthImage);

        String manFifthImage = getHangmanFigure(4);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                "      |\n" +
                "      |\n" +
                "========\n", manFifthImage);

        String manSixthImage = getHangmanFigure(5);
        assertEquals("  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                " /    |\n" +
                "      |\n" +
                "========\n", manSixthImage);
        String manSeventhImage = getHangmanFigure(6);
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
        assertTrue(game.guess('a'));
        assertEquals("_ a _ _ _ ", game.getCurrentState());
    }

    /**
     * Test when input a wrong letter, the number of wrong guesses and all guesses will increase.
     */
    @Test
    void testGuessWrongLetter() {
        assertFalse(game.guess('z'));
        assertEquals(1, game.getAllGuesses());
        assertEquals(1, game.getWrongGuesses());
    }

    /**
     * Test whether the game is over after 6 wrong guesses.
     */
    @Test
    void testIsGameOver() {
        for (int i = 0; i < 6; i++) {
            game.guess('z');
        }
        assertTrue(game.isGameOver());
    }

    /**
     * Test whether the word is guessed.
     */
    @Test
    void testIsWordGuessed() {
        for (char c : "maven".toCharArray()) {
            game.guess(c);
        }
        assertTrue(game.isWordGuessed());
    }

    /**
     * Test whether the current state is correct when input two correct letters.
     */
    @Test
    void testGetCurrentState() {
        game.guess('m');
        game.guess('a');
        assertEquals("m a _ _ _ ", game.getCurrentState());
    }

    /**
     * Test input invalid character.
     */
    @Test
    void testInputInvalidCharacter() {
        assertFalse(game.guess('1'));
        assertFalse(game.guess('?'));
        assertFalse(game.guess('!'));
        assertFalse(game.guess(' '));
    }
}
