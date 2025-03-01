import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanGame extends JFrame {
    private static final int MAX_ATTEMPTS = 6;
    private String word;
    private StringBuilder guessedWord;
    private int attempts;
    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JTextField guessField;
    private JButton guessButton;

    public HangmanGame() {
        setTitle("Hangman Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeGame();

        wordLabel = new JLabel(guessedWord.toString());
        wordLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(wordLabel, BorderLayout.CENTER);

        attemptsLabel = new JLabel("Attempts left: " + attempts);
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(attemptsLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        guessField = new JTextField(1);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());
        inputPanel.add(new JLabel("Enter a letter: "));
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        guessField.addActionListener(new GuessButtonListener()); // Allow pressing Enter to guess
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void initializeGame() {
        word = selectWord();
        guessedWord = new StringBuilder("_".repeat(word.length()));
        attempts = MAX_ATTEMPTS;
    }

    private String selectWord() {
        // You can change this to a random word generator
        return "example";
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guess = guessField.getText().trim().toLowerCase();
            if (!isValidGuess(guess)) {
                JOptionPane.showMessageDialog(null, "Please enter a single letter.");
                return;
            }
            guessField.setText("");

            processGuess(guess.charAt(0));
            updateLabels();

            if (guessedWord.toString().equals(word)) {
                JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the word!");
                resetGame();
            } else if (attempts == 0) {
                JOptionPane.showMessageDialog(null, "Game Over! The word was: " + word);
                resetGame();
            }
        }

        private boolean isValidGuess(String guess) {
            return guess.length() == 1 && Character.isLetter(guess.charAt(0));
        }

        private void processGuess(char guess) {
            boolean correctGuess = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    guessedWord.setCharAt(i, guess);
                    correctGuess = true;
                }
            }
            if (!correctGuess) {
                attempts--;
            }
        }

        private void updateLabels() {
            wordLabel.setText(guessedWord.toString());
            attemptsLabel.setText("Attempts left: " + attempts);
        }
    }

    private void resetGame() {
        initializeGame();
        wordLabel.setText(guessedWord.toString());
        attemptsLabel.setText("Attempts left: " + attempts);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HangmanGame game = new HangmanGame();
            game.setVisible(true);
        });
    }
}