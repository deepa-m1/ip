package cupcake.ui;

public class Parser {
    //Parser does interpreting of user commands

    /** Text input by user */
    String userInput;


    /** The boolean to activate Java asserts */
    static final boolean asserts = true;

    /**
     * Creates new Parser object.
     *
     * @param userInput text input by user.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    //getter for whole userInput
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
     * Asserts if array has 2 elements as it will be split later.
     *
     * @param arr String array containing part of user input
     * @throws AssertionError If assertions fail.
     */
    public void assertValid(String[] arr) throws AssertionError {
        if (asserts) {
            assert arr.length > 1;
            assert arr[1] != null;
            assert !arr[1].isBlank() ;
        }
    }

    /**
     * Returns the index of task the instruction word (mark/unmark/delete) is to act on.
     *
     * @param word String instruction which can only be mark/delete/unmark as they specify number.
     * @return index of task.
     * @throws CupcakeException if mark/unmark/delete commands do not specify suitable index.
     */
    public int getNumber(String word) throws CupcakeException {
        //should handle case of not having number or number exceeding length of list
        if (this.userInput.strip().startsWith(word)) {
            try {
                if (this.userInput.trim().endsWith(word)) {
                    throw new CupcakeException( word + " incomplete");
                }

                String str = this.userInput.strip().split(" ", 2)[1];
                return Integer.parseInt(str);
            } catch (CupcakeException e) {
                //anyway gotta do try-catch print in main, so I just do that in main
                throw new CupcakeException("You must add the task number");
            }

        }

        //if -1 returned it means it wasn't a command word but rather an add task kind
        return -1;
    }

    /**
     * Returns To-do Task object based on user input.
     *
     * @return To-do Task object.
     */
    public Task getTodoTask() {
        try {
            //detail not specified after to-do command
            if (this.userInput.trim().endsWith("todo")) {
                throw new CupcakeException("todo msg incomplete");
            }
            String[] words = this.userInput.split(" ", 2);
            String descp = words[1];
            //assign taskInput
            return new ToDo(descp);
        } catch (CupcakeException e) {
            System.out.println("Welp!! You must specify a message for todo!\n" +
                    "E.g todo borrow book");
        }
        return new Task("empty");
    }

    /**
     * Returns Deadline Task object based on user input.
     *
     * @return Deadline Task object.
     */
    public Task getDeadlineTask() {
        try {
            //detail not specified after deadline
            if (this.userInput.trim().endsWith("deadline")) {
                throw new CupcakeException("deadline instruction incomplete");
            }
            String[] words = this.userInput.split(" ", 2);
            assertValid(words);
            String[] descpAndDue = words[1].split("/by", 2);
            assertValid(descpAndDue);
            String descp = descpAndDue[0];
            String due = descpAndDue[1];

            return new Deadline(descp, due);
        } catch (CupcakeException e) {
            System.out.println("Welp!! You must specify a message and due date & time for duke.ui.Deadline!\n" +
                    "E.g deadline proj submission /by 2025-09-01 1800");
        }
        return new Task("empty");
    }

    /**
     * Returns Event Task object based on user input.
     *
     * @return Event Task object.
     */
    public Task getEventTask() {
        try {
            //detail not specified after event
            if (this.userInput.trim().endsWith("event")) {
                throw new CupcakeException("event instruction incomplete");
            }
            String[] words = this.userInput.split(" ", 2);
            assertValid(words);
            String[] descpAndStartAndEnd = words[1].split("/from", 2);
            assertValid(descpAndStartAndEnd);
            String descp = descpAndStartAndEnd[0];
            String[] startAndEnd = descpAndStartAndEnd[1].split("/to", 2);
            assertValid(startAndEnd);
            String start = startAndEnd[0];
            String end = startAndEnd[1];

            return new Event(descp, start, end);
        } catch (CupcakeException e) {
            System.out.println("Welp!! You must specify a message, start date and end date for duke.ui.Event!\n" +
                    "E.g event splashdown meeting /from 2025-08-27 1700 /to 2025-09-23 1500");
        }
        return new Task("empty");
    }

    /**
     * Returns a new Task object based on user input.
     *
     * @return Task object.
     * @throws CupcakeException if add task keywords are incomplete.
     */
    public Task getTask() throws CupcakeException {
        Task taskInput = new Task("empty");
        //if task type is to-do
        if (this.userInput.strip().startsWith("todo")) {
            taskInput = getTodoTask();
        }

        //task is deadline
        else if (this.userInput.strip().startsWith("deadline")) {
            taskInput = getDeadlineTask();
        }

        //task is event
        else if (this.userInput.strip().startsWith("event")) {
            taskInput = getEventTask();
        } else {
            //for any other start command, we won't be able to handle
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
                        find
                        """);
            }
        }

        return taskInput;
    }

    /**
     * Returns the description keyword for find command.
     *
     * @return description keyword string.
     * @throws CupcakeException If description keyword not specified.
     */
    public String getDescp() throws CupcakeException {
        try {
            if (this.userInput.trim().endsWith("find")) {
                throw new CupcakeException("find incomplete");
            }
            return this.userInput.substring(5);
        } catch (CupcakeException e) {
            //anyway gotta do try-catch print in main so I just do that in main
            throw new CupcakeException("You must add the description keyword");
        }
    }
}
