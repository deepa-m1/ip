package duke.ui;

import java.util.ArrayList;

public class TaskList {
    //contains the task list & can do delete etc

    //fields
    private final ArrayList<Task> tasks;

    //constructor
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    //2nd constructor if I have no tasks yet
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    //logic of list command: just print
    public void list() {
        for(int i = 0; i < this.tasks.size(); i++) {
            int p = 1 + i;
            Task currtask = this.tasks.get(i);
            System.out.println(p + "." + currtask.toString());
        }
    }

    //logic of mark command
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


    //logic of unmark command
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


    //logic of delete command: interpret which index and go there to update status
    public void delete(int pos) throws CupcakeException {
        //I think coz easiest is just get number/pos from textInput itself
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

    //logic of adding a task into tasks
    public void add(Task indTask) {
        this.tasks.add(indTask);
    }

    //logic of adding All tasks in ArrayList<duke.ui.Task> to this.tasks
    public void addAllStored(ArrayList<Task> existingTasks) {
        this.tasks.addAll(existingTasks);
    }

    //logic of returning size of duke.ui.TaskList object
    public int size() {
        return this.tasks.size();
    }


    /**
     * Returns formatted String of all currently inputted tasks
     */
    public String currTaskListStr() {
        String str = "";
        for (Task currtask : this.tasks) {
            str = str.concat(currtask.toString() + "\n");
        }
        return str;
    }
}
