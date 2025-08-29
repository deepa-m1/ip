//to get user input to do echo
import java.io.IOException;
import java.util.Scanner;


public class Cupcake {
    //fields
    private Storage storage;


    public static void main(String[] args) {
        //asking for user's input with scanner object
        Scanner askInput = new Scanner(System.in);
        System.out.println("Hello! I'm Cupcake :)");

        TaskList tasks = new TaskList();
        Storage storedFile = new Storage("./data/Cupcake.txt");
        //getting the stored hard-disk file
        try {

            //read the file content as ArrayList & append it to taskList
            tasks.addAllStored(storedFile.getFileContent(storedFile.getFilePath()));
            System.out.println("Welcome back! What can I do for you?");
        } catch (IOException e) {
            System.out.println("""
                    Hey! If you are an new user, ignore this message!\s
                    -->Note that I could not access any previous tasks you inputted.\s
                       But you can still continue using me!\s
                    """
            );
        }

        //storing the actual input into a task array list
        String txtInput = askInput.nextLine();



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
                        System.out.println("Ooo! You must specify the task number after mark. \n" +
                                "E.g mark 2");
                    }
                    break;

                case "unmark":
                    try {
                        num = parseObj.getNumber();
                        tasks.unmark(num);
                    } catch (CupcakeException e) {
                        System.out.println("Ooo! You must specify the task number after unmark. \n" +
                                "E.g unmark 2");
                    }
                    break;

                case "delete":
                    try {
                        num = parseObj.getNumber();
                        tasks.delete(num);
                    } catch (CupcakeException e) {
                        System.out.println("Ooo! You must specify the task number after delete. \n" +
                                "E.g delete 2");
                    }
                    break;

                default:
                    //its a task word
                    Task taskInput = parseObj.getTask();
                    if (!taskInput.getDescription().equals("empty")) {
                        //coz if empty it means I went through throwing exceptions path
                        //if not empty then gd I actually had meaningful commands
                        tasks.add(taskInput);
                        System.out.println("Okay I have added: " + taskInput.toString());

                        if (tasks.size() == 1) {
                            System.out.println("You now have 1 task! Let's go!!!");
                        } else {
                            System.out.println("You now have " + tasks.size() + " tasks! Let's go!!!");
                        }
                    }
            }

            //prompt for nxt new input
            System.out.println("*****************\n" + "Anything else I may help you with?");
            txtInput = askInput.nextLine();


        }

        //since user input is Bye, write to storedFile new inputs
        try {
            String content = tasks.currTaskListStr();
            storedFile.writeToFile(content);
        } catch (IOException e) {
            System.out.println("Do note that I am unable to store your task inputs for future retrieval!");
        }
        System.out.println("Bye. Hope to see you again real soon!");

    }

}
