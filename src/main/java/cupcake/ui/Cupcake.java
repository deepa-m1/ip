package cupcake.ui;//to get user input to do echo
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Cupcake {
    //fields
    /** Hard-disk file is stored in Storage */
    private Storage storage;
    /** ArrayList<Task> of all the tasks user has added*/
    private TaskList tasks;
    /** the user interface*/
    private Ui ui;

    /** the GUI userInput*/
    private String userInput;

    /** the default System.out statement*/
    private PrintStream defaultStream = System.out;
    /** the Ui print statements array*/
    private ByteArrayOutputStream uiOutputArray = new ByteArrayOutputStream();
    /** the Ui print statements stream*/
    private PrintStream uiOutputStream = new PrintStream(uiOutputArray);

    /** the boolean to activate Java asserts*/
    static final boolean asserts = true;

    /**
     * Creates new Cupcake object.
     * Creates new Ui and Storage objects within.
     * Creates new TaskList object by passing in storage content.
     *
     * @param filePath path to the stored file in Hard-disk.
     */
    public Cupcake(String filePath) {
        //So that our output stream is reassigned
        System.setOut(uiOutputStream);

        ui = new Ui();
        storage = new Storage(filePath);
        ui.intro();


        try {
            tasks = new TaskList(storage.getFileContent(filePath));
            ui.printWelcomeBack();
            this.getResponse();
        } catch (FileNotFoundException e) {
            ui.printFileRetrieveError();
            tasks = new TaskList();
            this.getResponse();
        }
    }

    /**
     * Returns string version of print outputs.
     * @return String of print statements at that point in time.
     */
    public String getResponse() {
        //writes buffered output to the stream
        System.out.flush();
        String output = uiOutputArray.toString();
        return output;
    }


    //setter for userInput
    public void inputSetter(String input) {
        this.userInput = input;
    }

    /**
     * Resets the uiOutputArray so that it is empty for
     * subsequent user input event interaction.
     */
    public void resetOutputArray() {
        uiOutputArray.reset();
    }

    /**
     * Checks if number input by user is valid.
     * @param num the task number value specified by user in their input
     * @throws AssertionError if assertion failed
     */
    public void assertNumber(int num) throws AssertionError {
        if (asserts) {
            assert num > 0 && num <= tasks.size() : "Number was invalid";
        }
    }

    /**
     * Writes user's tasks into Hard-disk file once program exits.
     * If user's input was list then the tasks is printed.
     * For other user inputs, the tasks is updated.
     */
    public void run() {
        //does the interface situation

        //assert check that userInput is not empty
        if (asserts) {
            assert userInput != null;
            assert !userInput.isBlank() : "input is empty";
        }

        //storing the actual input into a task array list
        //while the userInput is not Bye we just print as it is
        if (!(userInput.equals("bye") || userInput.equals("Bye") || userInput.equals("BYE"))) {
            //duke.ui.Parser object
            Parser parseObj = new Parser(userInput);
            String keyWord = parseObj.getKeyWord();
            int num;

            switch (keyWord) {
                case "list":
                    tasks.list();
                    break;
                case "mark":
                    try {
                        num = parseObj.getNumber();
                        assertNumber(num);
                        tasks.mark(num);
                    } catch (CupcakeException e) {
                        ui.printNumberError();
                    }
                    break;
                case "unmark":
                    try {
                        num = parseObj.getNumber();
                        assertNumber(num);
                        tasks.unmark(num);
                    } catch (CupcakeException e) {
                        ui.printNumberError();
                    }
                    break;
                case "delete":
                    try {
                        num = parseObj.getNumber();
                        assertNumber(num);
                        tasks.delete(num);
                    } catch (CupcakeException e) {
                        ui.printNumberError();
                    }
                    break;
                case "find":
                    try {
                        String descp = parseObj.getDescp();
                        if (asserts) {
                            assert !descp.isBlank();
                        }

                        tasks.find(descp);
                    } catch (CupcakeException e) {
                        ui.printDescpError();
                    }
                    break;
                default:
                    //it's a task word
                    Task taskInput = new Task("empty");
                    try {
                        taskInput = parseObj.getTask();
                    } catch (CupcakeException e) {
                        System.out.println(e.getMessage());
                    }

                    if (!taskInput.getDescription().equals("empty")) {
                        //coz if empty it means I went through throwing exceptions path
                        //if not empty then gd I actually had meaningful commands
                        tasks.add(taskInput);
                        ui.printSuccessfullyAdded(taskInput, tasks.size());
                    }

            this.getResponse();
            }

            //prompt for nxt new input
            ui.formattedAsk();
            this.getResponse();

        }

        else {

            //since user input is Bye, write to storedFile new inputs
            try {
                String content = tasks.currTaskListStr();
                storage.writeToFile(content);
            } catch (IOException e) {
                ui.printCannotSaveFile();
            }
            ui.printBye();
        }

    }

    /**
     * Runs the program above.
     *
     * @param args expected arguments.
     */
    public static void main(String[] args) {
        new Cupcake("data/Cupcake.txt").run();
    }

}
