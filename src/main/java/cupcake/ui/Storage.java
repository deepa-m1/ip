package cupcake.ui;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;

public class Storage {
    //deals with loading tasks from the file and saving tasks in file

    //fields
    /** The path to the Hard-Disk file where we store user tasks */
    private final String filePath;

    /**
     * Creates new Storage object.
     *
     * @param filePath path to Hard-Disk file containing previous run's user tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //getter
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Writes the task's string format to the Hard-Disk file.
     * If Hard-Disk file does not exist then it is created.
     *
     * @param text String task content to write.
     * @throws IOException If file writing is interrupted.
     */
    public void writeToFileEnd(String text) throws IOException {
        //NOTE: if file did not exist then the file will be created by FileWriter
        System.out.println("The content that will be written:\n" + text);
        FileWriter fWriter = new FileWriter(this.filePath);
        fWriter.write(text);
        fWriter.close();
    }

    /**
     * Writes the task's string format to the Hard-Disk file after
     * every task that is updated.
     * If Hard-Disk file does not exist then it is created.
     *
     * @param text String task content to write.
     * @throws IOException If file writing is interrupted.
     */
    public void writeToFile(String text) throws IOException {
        //NOTE: if file did not exist then the file will be created by FileWriter
        FileWriter fWriter = new FileWriter(this.filePath);
        fWriter.write(text);
        fWriter.close();
    }

    /**
     * Returns Task object for that line in file.
     *
     * @param type The task type.
     * @param info The task description.
     * @return Task object.
     */
    public Task interpretAndCreateTask(char type, String info) {
        Task task;
        switch(type) {
            case 'T':
                //To-Do task then substring is just description
                task = new ToDo(info);
                break;
            case 'D':
                //cupcake.ui.Deadline task so must split substring info
                String[] descpAndBy = info.split("\\(by:",2);
                String descpD = descpAndBy[0];
                String lastPart = descpAndBy[1];
                String[] byAndBracket = lastPart.split("\\)",2);
                String by = byAndBracket[0];

                //we need to format back the dateTime
                DateTimeFormatter expectedFormat = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
                task = new Deadline(descpD, by, expectedFormat);
                break;
            case 'E':
                //cupcake.ui.Event task so substring has start and end time
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
                task = new Event(descpE, start, end, expectedFormatE);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return task;
    }

    /**
     * Returns ArrayList<Task> by adding tasks read from Hard-Disk file.
     *
     * @param filePath String path to locate file.
     * @throws FileNotFoundException if file cannot be found at path.
     */
    public ArrayList<Task> getFileContent(String filePath) throws FileNotFoundException {
        File myFile = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        //create a Scanner whereby source is our file
        Scanner s = new Scanner(myFile);
        while(s.hasNext()) {
            String currLine = s.nextLine();
            //note currLine wld be like [T][ ] taskDescp but we only want to pass in taskDescp
            //need to extract from currLine for type, status, descp etc.
            char type = currLine.charAt(1);
            char status = currLine.charAt(4);
            String info = currLine.substring(7);

            //issue is if to-do task I only have descp, if deadline I have by & so on
            //thus I need to break down string based on type
            Task currTask;
            currTask = interpretAndCreateTask(type, info);

            //set status of task
            if (status =='X') {
                currTask.setStatus(true);
            }

            //time to add currTask to taskList
            taskList.add(currTask);

        }

        return taskList;

    }

}
