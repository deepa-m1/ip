package cupcake.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    //Will inherit description & isDone
    //Fields
    protected LocalDateTime start;
    protected LocalDateTime end;

    //constructor
    public Event(String description, String start, String end) {
        super(description);
        DateTimeFormatter expectedFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(start.strip(), expectedFormat);
        this.end = LocalDateTime.parse(end.strip(), expectedFormat);
    }

    //2nd constructor for level-8 to read file
    public Event(String description, String start, String end, DateTimeFormatter inputFormat) {
        super(description);
        this.start = LocalDateTime.parse(start.strip(), inputFormat);
        this.end = LocalDateTime.parse(end.strip(), inputFormat);
    }

    //printing formatting
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm "))
                + "to:" + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
