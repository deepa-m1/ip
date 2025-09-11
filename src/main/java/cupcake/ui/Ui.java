package cupcake.ui;

import java.util.Scanner;

public class Ui  {
    //deals with user interaction-so taking in user input and printing outputs
    /*so deals with asking for input and welcome/print statements/error
    for file retrieve and successfully added/removed messages,
    prompt for new input/Bye
     */

    //fields
    /** Scanner object to get user input */
    private static Scanner askInput;


    /**
     * Creates the user interface object.
     */
    public Ui() {
        askInput = new Scanner(System.in);
    }

    /**
     * Prints the name of the bot.
     */
    public void intro() {
        System.out.println("Hello! I'm Cupcake :)");
    }

    /**
     * Prints a welcome back message.
     * Is printed if this is not the first run of the chatbot.
     */
    public void printWelcomeBack() {
        System.out.println("Welcome back! What can I do for you?");
    }

    /**
     * Prints error message stating Hard-Disk file could not be retrieved.
     */
    public void printFileRetrieveError() {
        System.out.println("""
                    Hey! If you are an new user, ignore this message!\s
                    -->Note that I could not access any previous tasks you inputted.\s
                       But you can still continue using me!\s
                    """
        );
    }

    /**
     * Prints error message stating cannot write to Hard Disk file.
     */
    public void printCannotSaveFile() {
        System.out.println("Do note that I am unable to store your task inputs for future retrieval!");
    }

    /**
     * Prints error message stating task index was not specified after commands like mark/unmark/delete.
     */
    public void printNumberError() {
        System.out.println("Ooo! You must specify the task number after command. \n" +
                "E.g mark 2 \n" + "E.g delete 3 \n" + "E.g unmark 2");
    }

    /**
     * Prints successful message that task could be added into TaskList.
     * Prints the number of total tasks inside the list of tasks .
     *
     * @param task Task object that was successfully added into ArrayList of tasks.
     * @param totalTasks Total number of tasks in ArrayList of tasks.
     */
    public void printSuccessfullyAdded(Task task, int totalTasks) {
        System.out.println("Okay I have added: " + task.toString());

        if (totalTasks == 1) {
            System.out.println("You now have 1 task! Let's go!!!");
        } else {
            System.out.println("You now have " + totalTasks + " tasks! Let's go!!!");
        }
    }

    /**
     * Prints Bye once user inputs bye.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again real soon!");
    }

    /**
     * Prints prompt to ask user to indicate another command.
     */
    public void formattedAsk() {
        System.out.println("*****************\n" + "Anything else I may help you with?");
    }

    //getter: get userInput
    public String getInput() {
        return askInput.nextLine();
    }

    //print missing descp for find command
    public void printDescpError() {
        System.out.println("Do specify the keyword in the task descriptions!\n" + "E.g find book");
    }

    public void printDuplicateCommand() {
        System.out.println("This task already exists! You can check using the list command.");
    }

}
