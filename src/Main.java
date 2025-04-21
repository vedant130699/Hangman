import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //JAVA HANGMAN GAME

        Scanner sc = new Scanner(System.in);
        String word = "pizza";
        Set<Character> guessedLetters = new HashSet<>();
        //wordstate to fill the blank letters after guessing
        ArrayList<Character> wordState = new ArrayList<>();
        int wrongGuesses = 0;

        for(int i =0; i< word.length(); i++) {
            wordState.add('_');
        }
//        System.out.println(wordState);

        System.out.println("Welcome to Java hangman");
        while(wrongGuesses<6) {


            System.out.println(getHangmanArt(wrongGuesses));
            System.out.print("Word: ");

            for(char c: wordState) {
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Guess a letter: " );
            char guess = sc.next().toLowerCase().charAt(0);
            System.out.println(guess);
            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter!");
                continue;
            }
            guessedLetters.add(guess);

            if(word.indexOf(guess) >= 0) {
                System.out.println("Correct guess!");

                for(int i =0; i<word.length(); i++){
                    if(word.charAt(i) == guess) {
                        wordState.set(i, guess);
                        System.out.println(wordState);
                    }
                }
                if (!wordState.contains('_')) {
                    System.out.println("Congratulations! You guessed the word: " + word);
                    break;
                }

            }else {
                wrongGuesses++;
                System.out.println("Wrong guess!!");
            }
        }

        if(wrongGuesses >=6) {
            System.out.println("GAME OVER!!");
            System.out.println(getHangmanArt(6));
            System.out.print("Word was: " + word);
        }



        sc.close();
    }

    public static String getHangmanArt(int wrongGuesses) {
        return switch (wrongGuesses) {
            case 0 -> """
                    
                    
                    
                    """;
            case 1 -> """
                        O
                        
                    
                    """;
            case 2 -> """
                        O
                        |
                    
                    """;
            case 3 -> """
                        O
                       /|
                    
                    """;
            case 4 -> """
                        O
                       /|\\
                    
                    """;
            case 5 -> """
                        O
                       /|\\
                       /
                    """;
            case 6 -> """
                        O
                       /|\\
                       / \\
                    """;
            default -> "";
        };
    }

}