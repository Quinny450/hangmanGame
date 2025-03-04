import java.util.Scanner;

public class HangmanGame
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int guesses = 6;

        System.out.println("Enter the secret word: ");
        String secretWord = input.nextLine();

        while (guesses > 0)
        {
            System.out.println("Enter a letter: ");
            char letter = input.next().charAt(0);

            if (secretWord.indexOf(letter) != -1)
            {
                System.out.println("Correct!");
            }
            else
            {
                guesses--;
                System.out.println("Incorrect! You have " + guesses + " guesses left.");
            }
        }
    }
}