public class Event extends Task {
    //will inherit description & isDone
    protected String start;
    protected String end;

    //constructor
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    //printing formatting
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
