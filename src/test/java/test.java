import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.Hangman;
import java.io.InputStream;
import java.util.Scanner;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static stacs.Hangman.getHangmanFigure;

/*
Add
if the letter has been guessed, the number of wrong guesses will not increase
if the input is an invalid character, the number of wrong guesses will not increase
* */
class test {
    private Hangman game;
    @BeforeEach
    void setUp() {
        String firstWord = getFirstWordFromTestFile();
        game = new Hangman(firstWord);
    }
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

    @Test
    void testNoGuessesAfterGameOver() {
        for (int i = 0; i < 6; i++) {
            game.guess('z');
        }
        assertFalse(game.guess('m'));
        assertEquals(6, game.getAllGuesses());
    }

    @Test
    void testGetWrongGuessesEnterRightWordTwice() {
        game.guess('a');
        game.guess('a');
        assertEquals(0, game.getWrongGuesses());
    }

    @Test
    void testGetAllGuessesEnterRightWordTwice() {
        game.guess('a');
        game.guess('a');
        assertEquals(2, game.getAllGuesses());
    }

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

    @Test
    void testGuessCorrectLetter() {
        assertTrue(game.guess('a'));
        assertEquals("_ a _ _ _ ", game.getCurrentState());
    }

    @Test
    void testGuessWrongLetter() {
        assertFalse(game.guess('z'));
        assertEquals(1, game.getAllGuesses());
    }

    @Test
    void testIsGameOver() {
        for (int i = 0; i < 6; i++) {
            game.guess('z');
        }
        assertTrue(game.isGameOver());
    }

    @Test
    void testIsWordGuessed() {
        for (char c : "maven".toCharArray()) {
            game.guess(c);
        }
        assertTrue(game.isWordGuessed());
    }

    @Test
    void testGetCurrentState() {
        game.guess('m');
        game.guess('a');
        assertEquals("m a _ _ _ ", game.getCurrentState());
    }

    @Test
    void testGetCurrentStateUpperWord() {
        game.guess('M');
        assertEquals("m _ _ _ _ ", game.getCurrentState());
    }

    @Test
    void testInputInvalidCharacter() {
        assertFalse(game.guess('1'));
        assertFalse(game.guess('?'));
        assertFalse(game.guess('!'));
        assertFalse(game.guess(' '));
    }
}
