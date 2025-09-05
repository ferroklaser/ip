package echo.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testIsDoneStatus() {
        Task task = new Task("description");
        assertFalse(task.isDone);

        task.markAsDone();
        assertTrue(task.isDone);

        task.markAsUndone();
        assertFalse(task.isDone);
    }
}
