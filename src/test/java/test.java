import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.Hangman;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class test {
    private Hangman game;
    @BeforeEach
    void setUp() {
        String firstWord = getFirstWordFromTestFile();
        game = new Hangman(firstWord);
    }

    private String getFirstWordFromTestFile() {
        try {
            File file = new File("/Users/amber/Desktop/semaster2/CS5031/Hangman/src/test/resources/wordlist-test.txt"); // 替换为实际路径
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                return scanner.nextLine().trim();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            fail("File not found: " + e.getMessage());
        }
        return null;
    }

    @Test
    void testGuessCorrectLetter() {
        assertTrue(game.guess('a'));
        assertEquals("_ a _ _ _ ", game.getCurrentState());
    }


    @Test
    void testGuessWrongLetter() {
        assertFalse(game.guess('z'));
        assertEquals(1, game.getWrongGuesses());
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
}
