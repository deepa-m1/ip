//to get user input to do echo
import java.io.IOException;
import java.util.Scanner;


public class Cupcake {
    //fields
    private Storage storage;


    public static void main(String[] args) {

        Ui userInterface = new Ui();

        //printing name
        Ui.intro();

        TaskList tasks = new TaskList();
        Storage storedFile = new Storage("./data/Cupcake.txt");
        //getting the stored hard-disk file
        try {

            //read the file content as ArrayList & append it to taskList
            tasks.addAllStored(storedFile.getFileContent(storedFile.getFilePath()));
            Ui.printWelcomeBack();
        } catch (IOException e) {
            Ui.printFileRetrieveError();
        }

        //storing the actual input into a task array list
        String txtInput = userInterface.getInput();

        //while the userInput is not Bye we just print as it is
        while (!(txtInput.equals("bye") || txtInput.equals("Bye") || txtInput.equals("BYE"))) {
            //Parser object
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
                        userInterface.printNumberError();
                    }
                    break;

                case "unmark":
                    try {
                        num = parseObj.getNumber();
                        tasks.unmark(num);
                    } catch (CupcakeException e) {
                        userInterface.printNumberError();
                    }
                    break;

                case "delete":
                    try {
                        num = parseObj.getNumber();
                        tasks.delete(num);
                    } catch (CupcakeException e) {
                        userInterface.printNumberError();
                    }
                    break;

                default:
                    //its a task word
                    Task taskInput = parseObj.getTask();
                    if (!taskInput.getDescription().equals("empty")) {
                        //coz if empty it means I went through throwing exceptions path
                        //if not empty then gd I actually had meaningful commands
                        tasks.add(taskInput);
                        userInterface.printSuccesfullyAdded(taskInput, tasks.size());
                    }
            }

            //prompt for nxt new input
            userInterface.formattedAsk();
            txtInput = userInterface.getInput();


        }

        //since user input is Bye, write to storedFile new inputs
        try {
            String content = tasks.currTaskListStr();
            storedFile.writeToFile(content);
        } catch (IOException e) {
            Ui.printCannotSaveFile();
        }
        userInterface.printBye();

    }

}
