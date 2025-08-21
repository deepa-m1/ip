//to get user input to do echo
import java.util.Scanner;
import java.util.ArrayList;

public class Cupcake {
    public static void main(String[] args) {
        //asking for user's input with scanner object
        Scanner askInput = new Scanner(System.in);
        System.out.println("Hello! I'm Cupcake :)\n" + "What can I do for you?");

        //storing the actual input into a task array list
        String txtInput = askInput.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();

        //while the userInput is not Bye we just print as it is
        while(!(txtInput.equals("bye") || txtInput.equals("Bye") || txtInput.equals("BYE"))) {
            //taskInput will reset each time(after new user input)
            Task taskInput = new Task(txtInput);

            //if input was list then printing instructions
            if(txtInput.equals("list")) {
                for(int i = 0; i < taskList.size(); i++) {
                    int p = 1 + i;
                    Task currtask = taskList.get(i);
                    System.out.println(p + ".[" + currtask.getStatus() + "] " +currtask.getDescription());
                }
            }

            //using Task functions if mark/unmark
            else if(txtInput.startsWith("mark")) {
                int num = (txtInput.charAt(5)) - '0';
                Task markTask = taskList.get(num-1);
                markTask.markAsDone();
                System.out.println("Wow look at you go champion!! I will mark it.\n" +
                      "[" + markTask.getStatus() + "] " + markTask.getDescription());
            }
            else if(txtInput.startsWith("unmark")) {
                int num = (txtInput.charAt(7)) - '0';
                Task unmarkTask = taskList.get(num-1);
                unmarkTask.unMark();
                System.out.println("Okay noted!! I will unmark it.\n" +
                      "[" + unmarkTask.getStatus() + "] " + unmarkTask.getDescription());
            }

            else {
                //if not list then add the user input into the list
                taskList.add(taskInput);
                System.out.println("added: " + txtInput);
            }

            //prompt for any other input
            System.out.println("*****************\n" + "Anything else I may help you with?");
            askInput = new Scanner(System.in);
            txtInput = askInput.nextLine();
        }

        System.out.println("Bye. Hope to see you again real soon!");

    }
}
