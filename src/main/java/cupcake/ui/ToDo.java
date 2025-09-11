package cupcake.ui;

public class ToDo extends Task {
    /* would inherit description, isDone since protected
     & all are public methods */

    /**
     * Creates new To-Do task
     *
     * @param description information on activity task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
