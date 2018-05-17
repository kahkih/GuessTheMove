import java.io.BufferedReader;
import java.io.File;
//reads contents of large files.
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("movies_2.txt"));
        int countWords = 0;
        while (reader.readLine() != null) countWords++;
        reader.close();
        //Count the amount of lines in the file, corresponds with amount of movies.

        int randomMovie = (int) (Math.random() * countWords);
        //creates a random number that will be used as input to get a line from the txt file.

        String line = Files.readAllLines(Paths.get("movies_2.txt")).get(randomMovie);
        //System.out.print(line);
        //get the random number as input to read the line after checking the entire document. Not good for large text.
        String answer = line.replaceAll("[a-zA-Z]", "_"); // replace each letter with an "_"
        //System.out.println(answer);

        System.out.println("I have chosen a film title, try to guess it! \nStart with guessing a letter."
                + "\nYou can make 10 wrong guesses. \n"+ answer.replaceAll(".(?!$)", "$0 "));
        //add spaces between all the characters to make the game visually more clear for the amount of characters.

        Scanner userInput = new Scanner(System.in);
        //Create scanner object to be able to record user input.
        char[] answerCharArray = answer.toCharArray();
        //Convert String to character array. This will make it easy to compare the user input

        ArrayList<String> obj = new ArrayList<>();
        //Create an array to store each user input.
        ArrayList<String> wrong = new ArrayList<>();
        //Create an array to store each wrong input.

        for (int n = 10; n > 0; ) {
            //the recordings of mistakes. maximum of 10 wrong guesses.
            while (!userInput.hasNext("[A-Za-z]")) {
                System.out.println("please guess one letter and/or not a number.");
                userInput.next().charAt(0);
            }
            //Loop if the input is anything other than a character. Used next instead of nextLine to make it work.
            String userChar = userInput.next();
            //take the user input and only characters not numbers.
            obj.add(userChar);
            //store the input in a string

            for (int i = -1; (i = line.indexOf(userChar, i)) >= 0; i++) {
                //So the first letter in the array is  0 and it needs to loop till the last character.
                //We use the userChar as input to compare with the String "line".
                char aGuess = line.charAt(i);
                //Get the char from the String line that is the same as the guess. If the guess is not similar this
                //will not show a char.
                answerCharArray[i] = aGuess;
                //put the correctly guessed letter in the char array "answerCharArray".
                answer = String.valueOf(answerCharArray);
                //make a String out of the char array "answerCharArray".
                //answerPrevious = String.valueOf(answerCharArray);
            }
            System.out.println(answer.replaceAll(".(?!$)", "$0 "));
            //Print out the String answer which is modified each time for a correct answer
            System.out.println("You have guessed the following letters: " + obj);
            if (answer.equals(line)) {
                System.out.println("You have won!");
                System.out.println("You had " + n + " guess(es) left.");
                //display the player has won and the results
                return;
                //setting the number of mistakes to max to exit the program. Not so nice but it works for now.
            } else if (line.contains(userChar)) {
                System.out.println("Great guess!");
            } else if (!line.contains(userChar)) {
                System.out.println("Bummer! \nWrong guess!");
                if (wrong.contains(userChar)){
                    ++n;
                    wrong.remove(userChar);
                    System.out.println("You already guessed \"" + userChar +"\" before.");
                }
                wrong.add(userChar);
                System.out.println("Set of wrong guessed letters: " + wrong);
                n--;
                //The user gets feedback on how many wrong guesses are left and which wrong guesses were made.
                //Each time the n counter gets less.
                System.out.println("You can make " + n + " wrong guess(es)");
                if (n == 0) {
                    System.out.println("You lose :( ! \nThe movie is:"
                            + " \n\"" + line.replaceAll(".(?!$)", "$0 ") + "\". \nGame over");
                    return;
                    //If all the player used all the wrong guesses the game ends.
                }
            }
        }
    }
}