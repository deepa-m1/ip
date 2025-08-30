package cupcake.ui;

public class ToDo extends Task {
    //wld inherit description, isDone since protected & all public methods
    //constructor
    public ToDo(String description) {
        super(description);
    }

    //got to change the print
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
