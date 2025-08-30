package cupcake.ui;

public class ToDo extends Task {
    //wld inherit description, isDone since protected & all public methods

    /**
     * Creates new To-Do task
     *
     * @param description information on activity task
     */
    public ToDo(String description) {
        super(description);
    }

<<<<<<< HEAD
=======
    //got to change the print
>>>>>>> branch-A-CodingStandard
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
