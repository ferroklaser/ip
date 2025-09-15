package echo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import echo.task.Deadline;
import echo.task.Task;
import echo.task.Todo;
import echo.tasklist.TaskList;

public class StorageTest {
    @TempDir
    Path tempDir;

    @BeforeEach
    public void clearFile() {
        File file = new File("tempDir/echo.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testConstructor() {
        new Storage("tempDir/echo.txt");
        File f = new File("tempDir/echo.txt");
        assertTrue(f.exists());
    }

    @Test
    public void testReadFile() throws java.io.IOException {
        Storage storage = new Storage("tempDir/echo.txt");
        File f = new File("tempDir/echo.txt");
        f.createNewFile();
        TaskList list = new TaskList(storage.readFile());
        assertTrue(list.getList().isEmpty());
    }

    @Test
    public void testSaveFile() {
        Storage storage = new Storage("tempDir/echo.txt");
        File f = new File("tempDir/echo.txt");

        Task task1 = new Todo("description");
        Task task2 = new Deadline("description",
                LocalDateTime.of(2025, 9, 15, 23, 59));

        List<Task> list = List.of(task1, task2);
        storage.saveFile(list);

        List<Task> savedList = storage.readFile();

        assertEquals(2, savedList.size());
        assertEquals("description", savedList.get(0).getDescription());
        assertEquals("description", savedList.get(1).getDescription());
    }
}
