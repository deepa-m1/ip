package cupcake.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    //will inherit description & isDone
    /** the start date and time of event*/
    protected LocalDateTime start;
    /** the end date and time of event*/
    protected LocalDateTime end;

    /**
     * Creates new Event object.
     *
     * @param description task activity information.
     * @param start start date and time of event.
     * @param end end date and time of event.
     */
    public Event(String description, String start, String end) {
        super(description);
        DateTimeFormatter expectedFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(start.strip(), expectedFormat);
        this.end = LocalDateTime.parse(end.strip(), expectedFormat);
    }

    /**
     * Creates new Event object.
     *
     * @param description task activity information.
     * @param start start date and time of event.
     * @param end end date and time of event.
     * @param inputFormat format of the due date and time detail being passed in.
     */
    public Event(String description, String start, String end, DateTimeFormatter inputFormat) {
        super(description);
        this.start = LocalDateTime.parse(start.strip(), inputFormat);
        this.end = LocalDateTime.parse(end.strip(), inputFormat);

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm "))
                + "to:" + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
