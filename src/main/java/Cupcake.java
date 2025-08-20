//to get user input to do echo
import java.util.Scanner;

public class Cupcake {
    public static void main(String[] args) {
        //asking for user's input with scanner object
        Scanner askInput = new Scanner(System.in);
        System.out.println("Hello! I'm Cupcake :)\n" + "What can I do for you?");

        //storing the actual input
        String userInput = askInput.nextLine();
        //while the userInput is not Bye we just print as it is
        while(!(userInput.equals("bye") || userInput.equals("Bye") || userInput.equals("BYE"))) {
            System.out.println(userInput);
            //prompt for another input
            System.out.println("*****************\n" + "Anything else I may help you with?");
            askInput = new Scanner(System.in);
            userInput = askInput.nextLine();
        }

        System.out.println("Bye. Hope to see you again real soon!");
    }
}
