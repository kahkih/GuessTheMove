import java.io.BufferedReader;
import java.io.File;
//reads contents of large files.
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("movies_2.txt");
        Scanner scanner = new Scanner(file);

        //Read the designated file.


        //  BufferedReader reader = new BufferedReader(new FileReader("movies_2.txt"));
       /* int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close(); */
        int randomMovie = (int) (Math.random() * 25);
        //creates a random number that will be used as input to get a line from the txt file.
        /*Previously figured out with the below code how many lines are in the txt file.

                String word =
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            //put all the lines after one another in the line String.
            line += line;
            //add up all the words from the String line.
        }

        System.out.println(line);*/


        String line = Files.readAllLines(Paths.get("movies_2.txt")).get(randomMovie);
        //System.out.print(line);
        //get the random number as input to read the line after checking the entire document. Not good for large text.

        String answer = line.replaceAll("[a-zA-Z]", "_"); // replace each letter with an "_"
        //System.out.println(answer);


        System.out.println(answer.replaceAll(".(?!$)", "$0 "));
        //add spaces between all the characters to make the game visually more clear for the amount of characters.

        System.out.println("I have chosen a film title. Try to guess it!");

        //reader.useDelimiter("");
        //Only takes one and first char input
        System.out.println("Guess a letter");
        System.out.println(answer);
        Scanner userInput = new Scanner(System.in);

        char[] answerCharArray = answer.toCharArray();
        char[] wordCharArray = line.toCharArray();
        //Convert String to character array. This will make it easy to compare the user input

        System.out.println("String is:" + line + " Character Array : "+ Arrays.toString(wordCharArray));
        System.out.println("You can make 10 wrong guesses");
        ArrayList<String> obj = new ArrayList<>();
        ArrayList<String> wrong = new ArrayList<>();

        for (int n = 10; n>0;) {
            //the recordings of mistakes. maximum of 10 wrong guesses.

            String userChar = userInput.nextLine();
            //take the user input

            obj.add(userChar);
            //store the input in a string

            for (int i = -1; (i = line.indexOf(userChar, i + 1)) != -1; i++) {
               //So the first letter in the array is  0 and it needs to loop till the last character which is -1.
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
            System.out.println(answer);
            //Print out the String answer which is modified each time for a correct answer

            System.out.println("You have guessed the following letters: " + obj);
            if (answer.equals(line)) {
                System.out.println("You have won!");
                //display you have won
                return;
                //setting the number of mistakes to max to exit the program. Not so nice but it works for now.
            } else if (line.contains(userChar)) {
                System.out.println("Great guess!");
            } else if (!line.contains(userChar)) {
                System.out.println("Bummer! \nWrong guess!");
                wrong.add(userChar);
                System.out.println("Set of wrong guessed letters: "+ wrong);
                n--;
                System.out.println("You can make " + n + " wrong guess(es)");
                if (n==0) {
                 System.out.println("You lose :( ! \nThe movie is" + " \"" + line + "\". \nGame over" );
                 return;
                 }
            }

        }

      /*  for (int n = 10; n >0; --n) {
        //This loop ensures the mistakes that a player can make before guessing the movie.
        //The user has 10 tries.
            String userChar = userInput.nextLine();
            char[] userCharArray = userChar.toCharArray();
            // The player can input using the keyboard. The input is turned to a char to compare with the array.
            for (int i = 0; i < wordCharArray.length; i++) {
            //Another loop to check the entire array for each user input. The loop needs to be the length of the array.
                //This works but it does not give me back the position this easy. The hint said to use "indexOf".
                if (wordCharArray[i] == userCharArray[0]) {
                    System.out.println("Nice! The movie contains the letter: " + userChar);
                    n++;
                    break;
                    //After a correct guess, the mistakes counter if corrected.
                } else if (n==1){
                    System.out.println("Nice try but no cigars! Game over");
                    //Feedback after the final mistake has been made.
                    break;
                } else if (i==wordCharArray.length -1) {
                    System.out.println("Try again");
                    //Feedback when a wrong guess has been made after looping the entire array.
                }

            }
        } */ // might use this one to combine with a indexOf. Need to see how it works.

       /* int wordLength = line.length();

        boolean contains = false;

        for (char c : wordCharArray) {
            String userChar = userInput.nextLine();
            char[] userCharArray = userChar.toCharArray();

            if (c == userCharArray[wordLength]) {
                System.out.println("Nice! The movie contains a letter: " + userCharArray + " !");
            } else {
                System.out.println("Try again!");
            }
        } */



        /*for (int n = 0; n < line.length(); n++) {

            String userChar = userInput.nextLine();
            char[] userCharArray = userChar.toCharArray();
            //Take userinput

            if (wordCharArray[n] == userCharArray[n]) {


                System.out.println("Correct letter!Hang in there!" + userCharArray[n]);
            }
            else {
                System.out.println("Try again");
            }
        }  /* This works but not as I want. It checks every letter one by one after each guess.
              it needs to check the entire word for me. This can also loop */
    }
}