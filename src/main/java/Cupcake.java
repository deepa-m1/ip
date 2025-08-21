//to get user input to do echo
import java.util.Scanner;
import java.util.ArrayList;

public class Cupcake {

    private static Task getTask(String txtInput) {
        Task taskInput;
        //if task type is to-do
        if(txtInput.startsWith("todo")) {
            String[] Words = txtInput.split(" ", 2);
            String descp = Words[1];
            //so I should have the toString method
            taskInput = new ToDo(descp);
        }

        //task is deadline
        else if(txtInput.startsWith("deadline")) {
            String[] Words = txtInput.split(" ", 2);
            String[] descpAndDue = Words[1].split("/by", 2);
            String descp = descpAndDue[0];
            String due = descpAndDue[1];
            taskInput = new Deadline(descp, due);
        }

        //task is event
        else if(txtInput.startsWith("event")) {
            String[] Words = txtInput.split(" ", 2);
            String[] descpAndStartAndEnd = Words[1].split("/from", 2);
            String descp = descpAndStartAndEnd[0];
            String[] startAndEnd = descpAndStartAndEnd[1].split("/to", 2);
            String start = startAndEnd[0];
            String end = startAndEnd[1];
            taskInput = new Event(descp, start, end);
        } else {
            taskInput = new Task(txtInput);
        }
        return taskInput;
    }



    public static void main(String[] args) {
        //asking for user's input with scanner object
        Scanner askInput = new Scanner(System.in);
        System.out.println("Hello! I'm Cupcake :)\n" + "What can I do for you?");

        //storing the actual input into a task array list
        String txtInput = askInput.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();

        //while the userInput is not Bye we just print as it is
        while(!(txtInput.equals("bye") || txtInput.equals("Bye") || txtInput.equals("BYE"))) {

            //if input was list then printing instructions
            if(txtInput.equals("list")) {
                for(int i = 0; i < taskList.size(); i++) {
                    int p = 1 + i;
                    Task currtask = taskList.get(i);
                    System.out.println(p + "." + currtask.toString());
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
                Task taskInput = getTask(txtInput);

                taskList.add(taskInput);
                System.out.println("Okay I have added: " + taskInput.toString());

                if(taskList.size()==1) {
                    System.out.println("You now have 1 task! Let's go!!!");
                } else {
                    System.out.println("You now have " + taskList.size() + " tasks! Let's go!!!");
                }

            }

            //prompt for any other input
            System.out.println("*****************\n" + "Anything else I may help you with?");
            askInput = new Scanner(System.in);
            txtInput = askInput.nextLine();


        }

        System.out.println("Bye. Hope to see you again real soon!");

    }

}
