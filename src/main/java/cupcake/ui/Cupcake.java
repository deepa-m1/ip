package cupcake.ui;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class Cupcake {
    //fields
    /** Hard-disk file is stored in Storage */
    private Storage storage;
    /** ArrayList<Task> of all the tasks user has added */
    private TaskList tasks;
    /** The user interface */
    private Ui ui;

    /** The GUI userInput */
    private String userInput;

    /** The Ui print statements array */
    private ByteArrayOutputStream uiOutputArray = new ByteArrayOutputStream();
    /** The Ui print statements stream */
    private PrintStream uiOutputStream = new PrintStream(uiOutputArray);

    /** The boolean to activate Java asserts */

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

     * @param num the task number value specified by user in their input.
     * @throws AssertionError If assertion failed.


     */
    public void assertNumber(int num) throws AssertionError {
        if (asserts) {
            assert num > 0 && num <= tasks.size() : "Number was invalid";
        }
    }


    /**
     * Executes user's input for commands involving a number such as mark/unmark/delete.
     *
     * @param obj Parser object which has the user input.
     * @param word Instruction keyword such as mark/unmark/delete.
     */
    public void executeNumKeyword(Parser obj, String word) {
        int num;
        try {
            num = obj.getNumber(word);
            assertNumber(num);

            //store diff lambda methods associated to keyword
            HashMap< String, KeywordExecution> methodStore = new HashMap<>();
            methodStore.put("mark", val -> tasks.mark(val));
            methodStore.put("unmark", val -> tasks.unmark(val));
            methodStore.put("delete", val -> tasks.delete(val));

            //apply the specific method
            KeywordExecution lambda = methodStore.get(word);
            lambda.execute(num);
        } catch (CupcakeException e) {
            ui.printNumberError();
        }
    }

    /**
     * Interprets user's text input and executes them.
     *
     * @param word Instruction keyword such as list/mark/unmark/delete/find/event.
     * @param obj Parser object which has the user input.
     */
    public void interpretKeyword(String word, Parser obj) {
        switch (word) {
            case "list":
                tasks.list();
                break;
            case "mark":
                executeNumKeyword(obj, "mark");
                break;
            case "unmark":
                executeNumKeyword(obj, "unmark");
                break;
            case "delete":
                executeNumKeyword(obj, "delete");
                break;
            case "find":
                try {
                    String descp = obj.getDescp();
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
                    taskInput = obj.getTask();
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

            interpretKeyword(keyWord, parseObj);


            //prompt for nxt new input
            ui.formattedAsk();
            this.getResponse();

        } else {
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
