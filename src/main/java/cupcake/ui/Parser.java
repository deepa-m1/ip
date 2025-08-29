package cupcake.ui;

public class Parser {
    //interpreting user commands

    /** Text input by user*/
    String userInput;

    /**
     * Creates new Parser object.
     *
     * @param userInput text input by user.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    //getter
    public String getUserInput() {
        return this.userInput;
    }

    /**
     * Returns the instruction command word such as list/mark.
     *
     * @return instruction word.
     */
    public String getKeyWord() {
        return this.userInput.strip().split(" ", 2)[0];
    }


    /**
     * Returns the index of task the instruction word (mark/unmark/delete) is to act on.
     *
     * @return index of task.
     * @throws CupcakeException if mark/unmark/delete commands do not specify suitable index.
     */
    public int getNumber() throws CupcakeException {
        //should handle case of not having number or number exceeding length of list
        if(this.userInput.startsWith("mark")) {
            try {
                if(this.userInput.endsWith("mark")) {
                    throw new CupcakeException("mark incomplete");
                }

                String str = this.userInput.strip().split(" ", 2)[1];
                return Integer.parseInt(str);
            } catch (CupcakeException e) {
                //anyway gotta do try-catch print in main, so I just do that in main
                throw new CupcakeException("You must add the task number");
            }

        }

        if(this.userInput.startsWith("unmark")) {
            try {
                if (this.userInput.endsWith("unmark")) {
                    throw new CupcakeException("unmark incomplete");
                }

                String str = this.userInput.strip().split(" ", 2)[1];
                return Integer.parseInt(str);
            } catch (CupcakeException e) {
                //anyway gotta do try-catch print in main, so I just do that in main
                throw new CupcakeException("You must add the task number");
            }

        }


        if(this.userInput.startsWith("delete")) {
            try {
                if (this.userInput.endsWith("delete")) {
                    throw new CupcakeException("delete incomplete");
                }

                String str = this.userInput.strip().split(" ", 2)[1];
                return Integer.parseInt(str);
            } catch (CupcakeException e) {
                //anyway gotta do try-catch print in main, so I just do that in main
                throw new CupcakeException("You must add the task number");
            }

        }

        //if -1 returned it means it wasn't a command word but rather like an add task kind
        return -1;
    }


    //getTask method so that I can convert String userInput into a duke.ui.Task

    /**
     * Returns a new Task object based on user input.
     *
     * @return Task object.
     * @throws CupcakeException if add task keywords are incomplete.
     */
    public Task getTask() throws CupcakeException {
        Task taskInput = new Task("empty");
        //if task type is to-do
        if(this.userInput.startsWith("todo")) {
            try {
                //dh anything else except to-do
                if (this.userInput.endsWith("todo")) {
                    throw new CupcakeException("todo msg incomplete");
                }
                String[] Words = this.userInput.split(" ", 2);
                String descp = Words[1];
                //so I should have the toString method
                taskInput = new ToDo(descp);
            } catch (CupcakeException e) {
                System.out.println("Welp!! You must specify a message for todo!\n" +
                        "E.g todo borrow book");
            }
        }

        //task is deadline
        else if(this.userInput.startsWith("deadline")) {
            try {
                //dh anything else except deadline
                if (this.userInput.endsWith("deadline")) {
                    throw new CupcakeException("deadline instruction incomplete");
                }
                String[] Words = this.userInput.split(" ", 2);
                String[] descpAndDue = Words[1].split("/by", 2);
                String descp = descpAndDue[0];
                String due = descpAndDue[1];
                taskInput = new Deadline(descp, due);
            } catch (CupcakeException e) {
                System.out.println("Welp!! You must specify a message and due date & time for duke.ui.Deadline!\n" +
                        "E.g deadline proj submission /by 2025-09-01 1800");
            }
        }

        //task is event
        else if(this.userInput.startsWith("event")) {
            try {
                //dh anything else except event
                if (this.userInput.endsWith("event")) {
                    throw new CupcakeException("event instruction incomplete");
                }
                String[] Words = this.userInput.split(" ", 2);
                String[] descpAndStartAndEnd = Words[1].split("/from", 2);
                String descp = descpAndStartAndEnd[0];
                String[] startAndEnd = descpAndStartAndEnd[1].split("/to", 2);
                String start = startAndEnd[0];
                String end = startAndEnd[1];
                taskInput = new Event(descp, start, end);
            } catch (CupcakeException e) {
                System.out.println("Welp!! You must specify a message, start date and end date for duke.ui.Event!\n" +
                        "E.g event splashdown meeting /from 2025-08-27 1700 /to 2025-09-23 1500");
            }
        } else {
            //for any other start command, we shall not bother
            try {
                throw new CupcakeException("random input");
            } catch (CupcakeException e) {
                System.out.println("""
                        Hey buddy, I am sorry but I cannot handle that :(
                        You may start your input only with one of the following:
                        todo
                        deadline
                        event
                        list
                        mark
                        unmark
                        delete
                        """);
            }
        }
        return taskInput;
    }
}
