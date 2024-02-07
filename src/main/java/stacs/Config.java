package stacs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * A class to represent the configuration of the hangman game.
 * */
public class Config {
    private Properties properties;// The properties

    /**
     * Create a new configuration.
     * @throws IOException If the config.properties file cannot be found.
     * */
    public Config() throws IOException {
        this.properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Could not find config.properties");
            }
            this.properties.load(input);
        }
    }

    /**
     * Get the maximum number of attempts allowed.
     * @return The maximum number of attempts allowed.
     * */
    public int getMaxAttempts() {
        return Integer.parseInt(properties.getProperty("maxAttempts", "6"));
    }

    /**
     * Get the path to the word list file.
     * @return The path to the word list file.
     * */
    public String getWordListPath() {
        return properties.getProperty("wordListPath", "wordlist.txt");
    }

}
