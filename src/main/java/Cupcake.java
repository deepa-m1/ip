//to get user input to do echo
import java.util.Scanner;
import java.util.ArrayList;

public class Cupcake {
    public static void main(String[] args) {
        //asking for user's input with scanner object
        Scanner askInput = new Scanner(System.in);
        System.out.println("Hello! I'm Cupcake :)\n" + "What can I do for you?");

        //storing the actual input
        String userInput = askInput.nextLine();
        ArrayList<String> inputList = new ArrayList<>();

        //while the userInput is not Bye we just print as it is
        while(!(userInput.equals("bye") || userInput.equals("Bye") || userInput.equals("BYE"))) {
            //if input was list then printing instructions
            if(userInput.equals("list")) {
                for(int i = 0; i < inputList.size();i++) {
                    int p = 1 + i;
                    System.out.println(p + ". " + inputList.get(i));
                }
            } else {
                //if not list then add the user input into the list
                inputList.add(userInput);
                System.out.println("added: " + userInput);
            }

            //prompt for another input
            System.out.println("*****************\n" + "Anything else I may help you with?");
            askInput = new Scanner(System.in);
            userInput = askInput.nextLine();
        }

        System.out.println("Bye. Hope to see you again real soon!");

    }
}
