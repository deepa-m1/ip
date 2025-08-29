package cupcake.ui;

public class Task {
    //the characteristics of a duke.ui.Task -fields
    protected String description;
    protected boolean isDone;

    //constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //getters since protected fields
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    //setters since reading of file
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
