//to get user input to do echo
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Cupcake {

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


    /**
     * Returns a File with stored user input if any
     * If such a file doesnt exist, a new file is created and returned.
     *
     * @return File
     * @throws IOException if file creation is interrupted
     */
    public static File getFile() throws IOException {

        //creating File object
        File f = new File("./data/Cupcake.txt");

        //check if file doesn't exist
        if(!f.exists()) {
            //create the file
            if(f.createNewFile()) {
                return f;
            } else {
                throw new IOException("Could not create an hard-disk file to permanently store inputs");
            }
        }

        return f;

    }

    /**
     * Returns nothing
     * given args it appends contents to that file if it exists
     * @param text the String content to write
     * @throws IOException if file writing is interrupted
     */
    private static void writeToFile(String text) throws IOException {
        //NOTE: if file did not exist then the file will be created by FileWriter
        System.out.println("The content that will be written:\n" + text);
        FileWriter fWriter = new FileWriter("./data/Cupcake.txt");
        fWriter.write(text);
        fWriter.close();
    }

    /**
     * Returns ArrayList<Task> using the txt in File input
     * @param filePath the String path to locate file
     * @throws FileNotFoundException if file cannot be found at path
     */
    private static ArrayList<Task> getFileContent(String filePath) throws FileNotFoundException {
        File myFile = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        //create a Scanner whereby source is our file
        Scanner s = new Scanner(myFile);
        while(s.hasNext()) {
            String currLine = s.nextLine();
            //note currLine wld be like [T][ ] taskDescp, we only want to pass in taskDescp
            //need to extract from currLine for type, status, descp etc
            //currLine is roughly [T][ ] descp
            char type = currLine.charAt(1);
            char status = currLine.charAt(4);
            String info = currLine.substring(7);

            //issue is if to-do task i only have descp, if deadline i have by & so on
            //thus I need to break down string based on type
            Task currTask;
            switch(type) {
                case 'T':
                    //To-Do task then substring is just description
                    currTask = new ToDo(info);
                    break;

                case 'D':
                    //Deadline task so must split substring info
                    String[] descpAndBy = info.split("\\(by:",2);
                    String descpD = descpAndBy[0];
                    String lastPart = descpAndBy[1];
                    String[] byAndBracket = lastPart.split("\\)",2);
                    String by = byAndBracket[0];

                    //we need to format back the dateTime
                    DateTimeFormatter expectedFormat = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
                    currTask = new Deadline(descpD, by, expectedFormat);
                    break;

                case 'E':
                    //Event task so substring has start and end time
                    String[] descpAndDuration = info.split("\\(from:", 2);
                    String descpE = descpAndDuration[0];
                    String duration = descpAndDuration[1];
                    String[] fromAndToBracket = duration.split("to:",2);
                    String start = fromAndToBracket[0];
                    String endWithBracket = fromAndToBracket[1];
                    String[] endPart = endWithBracket.split("\\)",2);
                    String end = endPart[0];

                    //we need to format back the dateTime
                    DateTimeFormatter expectedFormatE = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
                    currTask = new Event(descpE, start, end, expectedFormatE);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }

            //set status of task
            if(status=='X') {
                currTask.setStatus(true);

            }

            //time to add currTask to taskList
            taskList.add(currTask);

        }

        return taskList;

    }

    /**
     * Returns formatted String of all currently inputted tasks
     * @param taskList which is ArrayList of all the inputted tasks in this session
     */
    public String currTaskListStr(ArrayList<Task> taskList) {
        String str = "";
        for(int i = 0; i < taskList.size(); i++) {
            int p = 1 + i;
            Task currtask = taskList.get(i);
            str = str.concat(currtask.toString() + "\n");
        }

        return str;
    }

    public static void main(String[] args) {
        Cupcake session = new Cupcake();
        //asking for user's input with scanner object
        Scanner askInput = new Scanner(System.in);
        System.out.println("Hello! I'm Cupcake :)" );

        ArrayList<Task> taskList = new ArrayList<>();
        File storedFile;
        //getting the stored hardisk file
        try {
            storedFile = getFile();

            //read the file content as ArrayList & append it to taskList
            taskList.addAll(getFileContent(storedFile.getPath()));
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
                for(int i = 0; i < taskList.size(); i++) {
                    int p = 1 + i;
                    Task currtask = taskList.get(i);
                    System.out.println(p + "." + currtask.toString());
                }
            }

            //using Task functions if mark/unmark/delete
            else if(txtInput.startsWith("mark")) {
                try {
                    if (txtInput.endsWith("mark")) {
                        throw new CupcakeException("mark incomplete");
                    }
                    int num = (txtInput.charAt(5)) - '0';
                    Task markTask = taskList.get(num - 1);
                    markTask.markAsDone();
                    System.out.println("Wow look at you go champion!! I will mark it.\n" +
                            markTask.toString());
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
                    Task unmarkTask = taskList.get(num - 1);
                    unmarkTask.unMark();
                    System.out.println("Okay noted!! I will unmark it.\n" +
                            unmarkTask.toString());
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
                    if(taskList.isEmpty() || num > taskList.size()) {
                        throw new CupcakeException("delete no specified doesn't exist");
                    }
                    Task removeTask = taskList.remove(num - 1);
                    System.out.println("Okay noted!! I will DELETE this from the list.\n" +
                            removeTask.toString());
                    System.out.println("You now only have " + taskList.size() + " number of tasks.");
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
                    //if not empty then gd i actually had meaningful commands
                    taskList.add(taskInput);
                    System.out.println("Okay I have added: " + taskInput.toString());

                    if(taskList.size()==1) {
                        System.out.println("You now have 1 task! Let's go!!!");
                    } else {
                        System.out.println("You now have " + taskList.size() + " tasks! Let's go!!!");
                    }
                }


            }

            //prompt for any other input
            System.out.println("*****************\n" + "Anything else I may help you with?");
            txtInput = askInput.nextLine();


        }

        //since user input is Bye, write to storedFile new inputs
        try {
            String content = session.currTaskListStr(taskList);
            writeToFile(content);
        } catch (IOException e) {
            System.out.println("Do note that I am unable to store your task inputs for future retrieval!");
        }
        System.out.println("Bye. Hope to see you again real soon!");

    }

}
