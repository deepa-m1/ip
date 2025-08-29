//to get user input to do echo
import java.io.IOException;
import java.util.Scanner;


public class Cupcake {
    //fields
    private Storage storage;

    private static Task getTask(String txtInput) {
        Task taskInput = new Task("empty");
        //if task type is to-do
        if(txtInput.startsWith("todo")) {
            try {
                //dh anything else except to-do
                if (txtInput.endsWith("todo")) {
                    throw new CupcakeException("todo msg incomplete");
                }
                String[] Words = txtInput.split(" ", 2);
                String descp = Words[1];
                //so I should have the toString method
                taskInput = new ToDo(descp);
            } catch (CupcakeException e) {
                System.out.println("Welp!! You must specify a message for todo!\n" +
                        "E.g todo borrow book");
            }
        }

        //task is deadline
        else if(txtInput.startsWith("deadline")) {
            try {
                //dh anything else except deadline
                if (txtInput.endsWith("deadline")) {
                    throw new CupcakeException("deadline instruction incomplete");
                }
                String[] Words = txtInput.split(" ", 2);
                String[] descpAndDue = Words[1].split("/by", 2);
                String descp = descpAndDue[0];
                String due = descpAndDue[1];
                taskInput = new Deadline(descp, due);
            } catch (CupcakeException e) {
                System.out.println("Welp!! You must specify a message and due date & time for Deadline!\n" +
                        "E.g deadline proj submission /by 2025-09-01 1800");
            }
        }

        //task is event
        else if(txtInput.startsWith("event")) {
            try {
                //dh anything else except event
                if (txtInput.endsWith("event")) {
                    throw new CupcakeException("event instruction incomplete");
                }
                String[] Words = txtInput.split(" ", 2);
                String[] descpAndStartAndEnd = Words[1].split("/from", 2);
                String descp = descpAndStartAndEnd[0];
                String[] startAndEnd = descpAndStartAndEnd[1].split("/to", 2);
                String start = startAndEnd[0];
                String end = startAndEnd[1];
                taskInput = new Event(descp, start, end);
            } catch (CupcakeException e) {
                System.out.println("Welp!! You must specify a message, start date and end date for Event!\n" +
                        "E.g event splashdown meeting /from 2025-08-27 1700 /to 2025-09-23 1500");
            }
        } else {
            //for any other start command, we shall not bother
            try {
                throw new CupcakeException("random input");
            } catch (CupcakeException e) {
                System.out.println("""
                        Hey buddy, I am sorry but I cannot handle that :(
                        You may start your input only with one of the following:
                        todo
                        deadline
                        event
                        list
                        mark
                        unmark
                        delete
                        """);
            }
        }
        return taskInput;
    }



    public static void main(String[] args) {
        //asking for user's input with scanner object
        Scanner askInput = new Scanner(System.in);
        System.out.println("Hello! I'm Cupcake :)" );

        TaskList tasks = new TaskList();
        Storage storedFile = new Storage("./data/Cupcake.txt");
        //getting the stored hardisk file
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
        while(!(txtInput.equals("bye") || txtInput.equals("Bye") || txtInput.equals("BYE"))) {

            //if input was list then printing instructions
            if(txtInput.equals("list")) {
                tasks.list();
            }

            //using Task functions if mark/unmark/delete
            else if(txtInput.startsWith("mark")) {
                try {
                    if (txtInput.endsWith("mark")) {
                        throw new CupcakeException("mark incomplete");
                    }
                    int num = (txtInput.charAt(5)) - '0';
                    tasks.mark(num);
                } catch (CupcakeException e) {
                    System.out.println("Ooo! You must specify the task number after mark. \n" +
                            "E.g mark 2");
                }
            }
            else if(txtInput.startsWith("unmark")) {
                try {
                    if (txtInput.endsWith("unmark")) {
                        throw new CupcakeException("unmark incomplete");
                    }
                    int num = (txtInput.charAt(7)) - '0';
                    tasks.unmark(num);
                } catch (CupcakeException e) {
                    System.out.println("Ooo! You must specify the task number after unmark. \n" +
                            "E.g unmark 2");
                }
            }
            //if input is delete
            else if(txtInput.startsWith("delete")) {
                try {
                    if (txtInput.endsWith("delete")) {
                        throw new CupcakeException("delete incomplete");
                    }

                    int num = (txtInput.charAt(7)) - '0';
                    tasks.delete(num);
                } catch (CupcakeException e) {
                    System.out.println("Ooo! You must specify the task number after delete and ensure " +
                            "the number exists. \n" + "E.g delete 1");
                }
            }

            else {
                //if not list then add the user input into the list
                Task taskInput = getTask(txtInput);
                if(!taskInput.getDescription().equals("empty")) {
                    //coz if empty it means I went through throwing exceptions path
                    //if not empty then gd I actually had meaningful commands
                    tasks.add(taskInput);
                    System.out.println("Okay I have added: " + taskInput.toString());

                    if(tasks.size()==1) {
                        System.out.println("You now have 1 task! Let's go!!!");
                    } else {
                        System.out.println("You now have " + tasks.size() + " tasks! Let's go!!!");
                    }
                }


            }

            //prompt for any other input
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
