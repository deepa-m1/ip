package cupcake.ui;

import java.util.ArrayList;

public class TaskList {
    //contains the task list & can do delete commands etc

    //fields
    /** The ArrayList containing all the task that user inputted */
    private final ArrayList<Task> tasks;

    /** The boolean to activate Java asserts */
    static final boolean asserts = true;

    /**
     * Creates new TaskList object.
     *
     * @param tasks ArrayList of task that user inputted.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints all the task items stored in tasks.
     */
    public void list() {
        if (asserts) {
            assert !this.tasks.isEmpty();
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            int p = 1 + i;
            Task currtask = this.tasks.get(i);
            System.out.println(p + "." + currtask.toString());
        }
    }

    /**
     * Sets isDone status of specified task to true.
     *
     * @param pos index of the specific task in the ArrayList tasks to be marked.
     * @throws CupcakeException If pos is not valid.
     */
    public void mark(int pos) throws CupcakeException {
        try {
            if(this.tasks.isEmpty() || pos > this.tasks.size()) {
                throw new CupcakeException("mark no specified doesn't exist");
            }
            Task markTask = this.tasks.get(pos - 1);
            markTask.markAsDone();
            System.out.println("Wow look at you go champion!! I will mark it.\n" +
                    markTask.toString());
        } catch (CupcakeException e) {
            System.out.println("Ooo! You must ensure the task number  exists. \n" +
                    "E.g mark 2");
        }

    }


    /**
     * Sets isDone status of specified task to false.
     *
     * @param pos index of the specific task in the ArrayList tasks to be marked.
     * @throws CupcakeException If pos is not valid.
     */
    public void unmark(int pos) throws CupcakeException {
        try {
            if(this.tasks.isEmpty() || pos > this.tasks.size()) {
                throw new CupcakeException("unmark no specified doesn't exist");
            }
            Task unmarkTask = this.tasks.get(pos - 1);
            unmarkTask.unMark();
            System.out.println("Okay noted!! I will unmark it.\n" +
                    unmarkTask.toString());
        } catch (CupcakeException e) {
            System.out.println("Ooo! You must ensure the task number exists. \n" +
                    "E.g unmark 2");
        }
    }


    /**
     * Removes the specified task from ArrayList<Task>.
     *
     * @param pos index of the specific task in the ArrayList tasks to be removed.
     * @throws CupcakeException If pos is not valid.
     */
    public void delete(int pos) throws CupcakeException {
        //easiest is just get number/pos from textInput itself
        try {
            if(this.tasks.isEmpty() || pos > this.tasks.size()) {
                throw new CupcakeException("delete no specified doesn't exist");
            }

            Task removeTask = this.tasks.remove(pos - 1);
            System.out.println("Okay noted!! I will DELETE this from the list.\n" +
                    removeTask.toString());
            System.out.println("You now only have " + this.tasks.size() + " number of tasks.");
        } catch (CupcakeException e) {
            System.out.println("Ooo! You must ensure " +
                    "the number exists. \n" + "E.g delete 1");
        }

    }

    /**
     * Adds the Task into the ArrayList tasks.
     *
     * @param indTask Task object to be added to ArrayList.
     */
    public void add(Task indTask) {
        this.tasks.add(indTask);
    }


    /**
     * Adds ALL tasks in some ArrayList<Task> to this.tasks.
     *
     * @param existingTasks tasks from Stored hard Disk file.
     */
    public void addAllStored(ArrayList<Task> existingTasks) {
        this.tasks.addAll(existingTasks);
    }

    /**
     * Returns size of the this.tasks.
     *
     * @return size of ArrayList<Task> tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns formatted String of all currently inputted tasks.
     */
    public String currTaskListStr() {
        String str = "";
        for (Task currtask : this.tasks) {
            str = str.concat(currtask.toString() + "\n");
        }
        return str;
    }

    /**
     * Finds for all tasks matching the task description given by user.
     *
     * @param descp The task description.
     * @throws CupcakeException If description is unsepcified.
     */
    public void find(String descp) throws CupcakeException {
        String matchingTasksStr = "";
        try {
            if (descp.isEmpty()) {
                throw new CupcakeException("description not specified");
            }
            Task currTask;
            for (int i = 0; i < this.tasks.size(); i++) {
                currTask = this.tasks.get(i);
                String currTaskDescp = currTask.getDescription();
                //check if the currTask description has descp
                if (currTaskDescp.contains(descp)) {
                    matchingTasksStr = matchingTasksStr.concat( i + "." + currTask.toString() + "\n");
                }
            }
            System.out.println(matchingTasksStr);
        } catch (CupcakeException e) {
            //anyway gotta do try-catch print in main so i just do that in main
            throw new CupcakeException("You must add the description keyword");
        }
    }

    public boolean detectDuplicate(String descp) {
        boolean val = false;
        for (Task task : this.tasks) {
            if (task.getDescription().equals(descp)) {
                //the task is already added before
                val = true;
                break;
            }
        }
        return val;
    }

}
