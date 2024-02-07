# Hangman Game Instructions

## Game Overview

Hangman is a word guessing game in which the player tries to guess a hidden word one letter at a time. At the start of the game, the word is replaced by an underline, indicating the length of the word. If the player guesses a letter that is in the word, all positions of that letter are revealed. If the guess is wrong, an image of a hangman is progressively drawn. The player can make a maximum of six mistakes, beyond which the game is over. The player's goal is to guess the word before the hangman is fully drawn.

## Installation and operation

### Version

hangman under the old package is an incomplete refactoring of the code for version 4, while hangman_new under the stacs package is a refactoring of the code for version 5.
Both versions can be tested and run, but the code in version 5 is much clearer and easier to understand.

### Run

To run the Hangman under the old package:
1. Download the game code to your local computer.
2. Using the command line interface, navigate to the folder containing the game code.
3. compile the Java code: `javac *.java`
4. Run the compiled programme: `java Hangman`

To run the Hangman_new under the stacs package:
1. Download the game code to your local computer.
2. Using the command line interface, navigate to the folder containing the game code(stacs package).
3. compile the Java code: `javac *.java`
4. Run the compiled programme: `java Hangman_new`

### Test tests
The tests in the test class are for hangman (the class that is not completely refactored) and the tests in the test_new class are for hangman_new (the refactored class).

To run the test class:
1. compile the Java code: `javac HangmanTest.java`
2. Run the compiled programme: `java HangmanTest`.
3. View the test results

Run the methods of the test_new class:
1. Compile the Java code: `javac HangmanTest_new.java`.
2. Run the compiled programme: `java HangmanTest_new`.
3. View the test results

## Game Instructions

During the game you can enter the following commands:

- Type single letters to guess words.
- Type `hint` to get a hint. Each player has one chance to use a hint.

## Test-driven development

During the development of Hangman game I followed the principles of Test Driven Development (TDD). I wrote initial test cases to verify the basic functionality of the game from day one of the project and gradually added tests for special cases such as handling of input in upper and lower case letters, handling of file paths, and guessing the logic in subsequent releases. These tests helped me ensure the correctness of the game's logic and the integrity of its basic functionality.

## Code refactoring

In version 4 of the project, we performed an in-depth code refactoring to improve the readability and maintainability of the code. We separated the game logic, user input processing, and game interface display into separate methods. In addition, we optimised the guessing methods to avoid code duplication and to make the logic of correct and incorrect guessing clearer. Javadoc comments were added to make the code easy to understand.
In the fifth version of the project, we further refactored the code by separating the game logic, user input processing and game interface display into different classes. This design makes the code clearer and easier to understand and maintain.

## New Features

Added the hint function, each player has one chance to use the hint, by typing `hint` to get a one-letter hint.

## References

[1] Wikipedia. Hangman (game). https://en.wikipedia.org/wiki/Hangman_(game) (accessed 7 February 2024)

