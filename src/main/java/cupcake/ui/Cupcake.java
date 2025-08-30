package cupcake.ui;//to get user input to do echo
import java.io.FileNotFoundException;
import java.io.IOException;


public class Cupcake {
    //fields
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    //constructor
    public Cupcake(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ui.intro();

        try{
          tasks = new TaskList(storage.getFileContent(filePath));
          ui.printWelcomeBack();
        } catch (FileNotFoundException e) {
            ui.printFileRetrieveError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //does the interface situation
        //storing the actual input into a task array list
        String txtInput = ui.getInput();

        //while the userInput is not Bye we just print as it is
        while (!(txtInput.equals("bye") || txtInput.equals("Bye") || txtInput.equals("BYE"))) {
            //duke.ui.Parser object
            Parser parseObj = new Parser(txtInput);
            String keyWord = parseObj.getKeyWord();
            int num;

            switch (keyWord) {
                case "list":
                    tasks.list();
                    break;

                case "mark":
                    try {
                        num = parseObj.getNumber();
                        tasks.mark(num);
                    } catch (CupcakeException e) {
                        ui.printNumberError();
                    }
                    break;

                case "unmark":
                    try {
                        num = parseObj.getNumber();
                        tasks.unmark(num);
                    } catch (CupcakeException e) {
                        ui.printNumberError();
                    }
                    break;

                case "delete":
                    try {
                        num = parseObj.getNumber();
                        tasks.delete(num);
                    } catch (CupcakeException e) {
                        ui.printNumberError();
                    }
                    break;

                case "find":
                    try {
                        String descp = parseObj.getDescp();
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
                        ui.printSuccesfullyAdded(taskInput, tasks.size());
                    }
            }

            //prompt for nxt new input
            ui.formattedAsk();
            txtInput = ui.getInput();


        }

        //since user input is Bye, write to storedFile new inputs
        try {
            String content = tasks.currTaskListStr();
            storage.writeToFile(content);
        } catch (IOException e) {
            ui.printCannotSaveFile();
        }
        ui.printBye();
    }


    public static void main(String[] args) {
        new Cupcake("data/Cupcake.txt").run();
    }

}
