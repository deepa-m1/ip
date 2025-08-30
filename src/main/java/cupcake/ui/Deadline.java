package cupcake.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    //will inherit string description, isDone
    /** Due date and time details for the task*/
    protected LocalDateTime by;

    /**
     * Creates new Deadline object.
     *
     * @param description task activity information.
     * @param by due date and time details.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter expectedFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by.strip(), expectedFormat);
    }

    /**
     * Creates new Deadline object.
     *
     * @param description task activity information.
     * @param by due date and time details.
     * @param inputFormat format of the due date and time detail being passed in.
     */
    public Deadline(String description, String by, DateTimeFormatter inputFormat) {
        super(description);
        this.by = LocalDateTime.parse(by.strip(), inputFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
