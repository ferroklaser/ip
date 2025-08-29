package echo.storage;

import echo.tasklist.TaskList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    public void testConstructor() {
        new Storage("tempDir/echo.txt");
        File f = new File("tempDir/echo.txt");
        assertTrue(f.exists());
    }

    @Test
    public void testReadFile() throws java.io.IOException{
        Storage storage = new Storage("tempDir/echo.txt");
        File f = new File("tempDir/echo.txt");
        f.createNewFile();
        TaskList list = new TaskList(storage.readFile());
        assertTrue(list.getList().isEmpty());
    }
}
