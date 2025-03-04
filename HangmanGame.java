import java.util.Scanner;

public class HangmanGame
{
    public static void main(String[] args)
    {
        // Initializing user input
        Scanner input = new Scanner(System.in);

        // Variable to store the number of guesses left
        int guesses = 6;

        // Prompting the user to enter the secret word
        System.out.println("Enter the secret word: ");
        String secretWord = input.nextLine();

        // Array to keep track of guessed letters
        boolean[] guessedLetters = new boolean[secretWord.length()];

        // Loop to keep the game running until the user runs out of guesses
        while (guesses > 0)
        {
            // Prompting the user to enter a letter
            System.out.println("Enter a letter: ");
            char letter = input.next().charAt(0);

            // Checking if the letter is in the secret word
            boolean correctGuess = false;
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == letter) {
                    guessedLetters[i] = true;
                    correctGuess = true;
                }
            }

            if (correctGuess)
            {
                System.out.println("Correct!");
            }
            else
            {
                guesses--;
                System.out.println("Incorrect! You have " + guesses + " guesses left.");
            }

            // Display the current state of the secret word
            StringBuilder displayWord = new StringBuilder();
            boolean allLettersGuessed = true;
            for (int i = 0; i < secretWord.length(); i++) {
                if (guessedLetters[i]) {
                    displayWord.append(secretWord.charAt(i));
                } else {
                    displayWord.append('_');
                    allLettersGuessed = false;
                }
            }
            System.out.println("Current word: " + displayWord.toString());

            // Check if all letters have been guessed
            if (allLettersGuessed) {
                System.out.println("Congratulations! You've guessed the word: " + secretWord);
                break;
            }
        }

        if (guesses == 0) {
            System.out.println("Game Over! The word was: " + secretWord);
        }
    }
}