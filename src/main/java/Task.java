public class Task {
    //the characteristics of a Task -fields
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
