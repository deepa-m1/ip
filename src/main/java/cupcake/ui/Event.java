package cupcake.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    //Will inherit description & isDone
    //Fields
    /** The start date and time of event */
    protected LocalDateTime start;
    /** The end date and time of event */
    protected LocalDateTime end;

    /** the boolean to activate Java's Assert */
    static final boolean isAsserts = false;

    /**
     * Checks if duration of Event is specified.
     *
     * @param begin the start date and time of the event.
     * @param finish the end date and time of the event.
     * @throws AssertionError If assert fails.
     */
    public void assertDuration(String begin, String finish) throws AssertionError {
        if (isAsserts) {
            assert begin != null || finish != null : "the time info is null";
            assert !begin.isBlank() || !finish.isBlank() : "the time info is empty";
        }
    }

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
        assertDuration(start,end);
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
        assertDuration(start, end);
        this.start = LocalDateTime.parse(start.strip(), inputFormat);
        this.end = LocalDateTime.parse(end.strip(), inputFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm "))
                + "to:" + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
