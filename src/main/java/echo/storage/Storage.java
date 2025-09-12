package echo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import echo.task.Deadline;
import echo.task.Event;
import echo.task.Task;
import echo.task.Todo;

/**
 *  Represents persistent storage to store and retrieve user tasks.
 *  The <code>Storage</code> class provides methods to read tasks from a file and
 *  write tasks to a file, allowing the application to maintain task data between
 *  programs. A <code>Storage</code> object stores a File <code>file</code>
 *  from which the information is retrieved.
 */
public class Storage {
    private File file;

    public Storage(String path) {
        this.file = new File(path);

        if (!file.exists()) {
            try {
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    boolean newDir = parentFile.mkdirs();
                }
                boolean newFile = file.createNewFile();
            } catch (IOException error) {
                System.out.println("Unable to create new file!");
            }
        }
    }

    /**
     * Returns a list of tasks after reading the file from the storage.
     *
     * @return tasks List of tasks stored in file.
     */
    public List<Task> readFile() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                case "T":
                    Task todoTask = new Todo(parts[2]);
                    if (parts[1].equals("1")) {
                        todoTask.markAsDone();
                    }
                    list.add(todoTask);
                    break;
                case "D":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
                    LocalDateTime deadline = LocalDateTime.parse(parts[3], formatter);
                    Task deadlineTask = new Deadline(parts[2], deadline);
                    if (parts[1].equals("1")) {
                        deadlineTask.markAsDone();
                    }
                    list.add(deadlineTask);
                    break;
                case "E":
                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
                    LocalDateTime start = LocalDateTime.parse(parts[3], formatter1);
                    LocalDateTime end = LocalDateTime.parse(parts[4], formatter1);
                    Task eventTask = new Event(parts[2], start, end);
                    if (parts[1].equals("1")) {
                        eventTask.markAsDone();
                    }
                    list.add(eventTask);
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException error) {
            System.out.println("File not found exception");
        }
        return list;
    }

    /**
     * Saves the given list of tasks by writing it onto the storage file
     *
     * @param list The list of tasks to be stored
     */
    public void saveFile(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(this.file);

            for (Task task : list) {
                fw.write(task.toDataString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
