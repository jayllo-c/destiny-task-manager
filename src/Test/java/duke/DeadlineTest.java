package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getBy_validInput_properDates() throws DukeException {
        assertEquals("29/10/2024 1234", new Deadline("test",
                "29/10/2024 1234").getBy());
    }

    @Test
    public void getBy_invalidInput_exceptionThrown() {
        try {
            assertEquals("29/10/2024 1234", new Deadline("test",
                    "33/13/2024 4567").getBy());
        } catch (DukeException e) {
            assertEquals("Something went wrong...\n" +
                    "Please enter the date and time in the following format:\n" +
                    "dd/mm/yyyy hhmm (e.g. 30/01/2024 1234", e.getMessage());
        }
    }
}
