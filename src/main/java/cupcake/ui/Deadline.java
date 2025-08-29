package cupcake.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    //will inherit string description, isDone
    protected LocalDateTime by;

    //constructor
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter expectedFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by.strip(), expectedFormat);
    }

    //2nd onstructor for level-8 to read file
    public Deadline(String description, String by, DateTimeFormatter inputFormat) {
        super(description);
        this.by = LocalDateTime.parse(by.strip(), inputFormat);
    }

    //rewriting formatting
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
