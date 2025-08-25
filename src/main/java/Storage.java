import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.List;


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
                        Task deadlineTask = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) {
                            deadlineTask.markAsDone();
                        }
                        list.add(deadlineTask);
                        break;
                    case "E":
                        Task eventTask = new Event(parts[2], parts[3], parts[4]);
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
