package cupcake.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ParserTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void resetSystemOutput() {
        System.setOut(originalOutput);
    }

    @Test
    public void interpret() {
        //Test case 1: typical
        try {
            assertEquals("[D][ ] vid submission (by:Oct 21 2025 16:00)",
                    new Parser("deadline vid submission /by 2025-10-21 1600").getTask().toString());
        } catch (CupcakeException e) {
            System.out.println(e.getMessage());
        }

    }

    //TODO: not sure how to test exception cases
    @Test
    public void testExceptionIsThrown() {
        try {
            new Parser("event").getTask();
            String expectedOutput = "Welp!! You must specify a message, start date and end date for Event!\n" +
                    "E.g event splashdown meeting /from 2025-08-27 1700 /to 2025-09-23 1500\n";
            assertEquals(expectedOutput, outputStream.toString());
        } catch (CupcakeException e) {
            System.out.println(e.getMessage());
        }
    }

}
