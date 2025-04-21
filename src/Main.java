import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //JAVA HANGMAN GAME

        String filePath = "src\\words.txt";

        ArrayList<String> words = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) !=null) {
                words.add(line.trim());
            }
        }catch (FileNotFoundException e) {
            System.out.println("Could not find the file");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

        Random random = new Random();
        String word = words.get(random.nextInt(0, words.size()));
        Scanner sc = new Scanner(System.in);
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