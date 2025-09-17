package cupcake.ui;

public class Task {
    //the characteristics of a cupcake.ui.Task: fields
    /** The information about the task activity */
    protected String description;
    /** The status of the task completion */
    protected boolean isDone;

    /** the boolean to activate Java's Assert */
    static final boolean isAsserts = true;

    /**
     * Creates new Task object.
     *
     * @param description information about taks activity.
     */
    public Task(String description) {
        if (isAsserts) {
            assert description != null;
            assert !description.isBlank();
        }
        this.description = description;
        this.isDone = false;
    }

    //getter: status
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    //getter: description
    public String getDescription() {
        return description;
    }

    //setters: sets status since reading of file
    public void setStatus(boolean val) {
        this.isDone = val;
    }

    //marking done
    public void markAsDone() {
        this.isDone = true;
    }

    //undoing accidental marking
    public void unMark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.getDescription();
    }
}
