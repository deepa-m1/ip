public class Deadline extends Task {
    //will inherit string description, isDone
    protected String by;

    //constructor
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    //rewriting formatting
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
