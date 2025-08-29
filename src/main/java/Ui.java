import java.util.Scanner;

public class Ui  {
    //deals with user interaction-so taking in user input and printing outputs
    /*so deal with asking for input and welcome/print statements/error
    for file retrieve and succesfully added/removed msgs/prompt for new input/Bye
     */

    //fields
    private String str; //the print statements
    private static Scanner askInput;


    //constructor: actually i dont see purpose of constructor
    public Ui() {
        askInput = new Scanner(System.in);
    }

    //print name
    public static void intro() {
        System.out.println("Hello! I'm Cupcake :)");
    }

    //print to Welcome user if file loaded- static coz doenst depend on Ui
    public static void printWelcomeBack() {
        System.out.println("Welcome back! What can I do for you?");
    }

    //print for file cannot be retrieved
    public static void printFileRetrieveError() {
        System.out.println("""
                    Hey! If you are an new user, ignore this message!\s
                    -->Note that I could not access any previous tasks you inputted.\s
                       But you can still continue using me!\s
                    """
        );
    }

    //print for file cannot store new data
    public static void printCannotSaveFile() {
        System.out.println("Do note that I am unable to store your task inputs for future retrieval!");
    }

    //print for number missing in instruction commands
    public void printNumberError() {
        System.out.println("Ooo! You must specify the task number after command. \n" +
                "E.g mark 2 \n" + "E.g delete 3 \n" + "E.g unmark 2");
    }

    //print succesful task added msg
    public void printSuccesfullyAdded(Task task, int totalTasks) {
        System.out.println("Okay I have added: " + task.toString());

        if (totalTasks == 1) {
            System.out.println("You now have 1 task! Let's go!!!");
        } else {
            System.out.println("You now have " + totalTasks + " tasks! Let's go!!!");
        }
    }

    //print for Bye input
    public void printBye() {
        System.out.println("Bye. Hope to see you again real soon!");
    }

    //to prompt for user nxt input with print
    public void formattedAsk() {
        System.out.println("*****************\n" + "Anything else I may help you with?");
    }


    //to get user input
    public String getInput() {
        return askInput.nextLine();
    }


}
