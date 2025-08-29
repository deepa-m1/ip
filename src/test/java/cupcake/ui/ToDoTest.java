package cupcake.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void taskRepTest() {
        //Test case 1
        assertEquals("[T][ ] sleep", new ToDo("sleep").toString());
    }
}
