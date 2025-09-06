package echo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import echo.command.Command;
import echo.echoexception.EchoException;
import echo.parser.Parser;
import echo.storage.Storage;
import echo.task.Deadline;
import echo.task.Event;
import echo.task.Task;
import echo.task.Todo;
import echo.tasklist.TaskList;
import echo.ui.UI;

public class Echo {
    protected final Storage storage;
    protected final UI ui;
    protected final TaskList tasklist;

    public Echo() {
        this.storage = new Storage("data/echo.txt");
        this.ui = new UI();
        this.tasklist = new TaskList(this.storage.readFile());
    }

    public void run() {
        this.ui.showWelcome();

        while (true) {
            try {
                String input = this.ui.readCommand();
                String[] parsedInput = Parser.parse(input);
                Command command = Command.valueOf(parsedInput[0]);
                switch (command) {
                case BYE:
                    ui.showExit();
                    storage.saveFile(this.tasklist.getList());
                    return;
                case LIST: {
                    ui.showList(this.tasklist);
                    break;
                }
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task t = null;
                    switch (command) {
                    case TODO:
                        t = new Todo(parsedInput[1]);
                        this.tasklist.addTask(t);
                        break;
                    case DEADLINE:
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime deadline = LocalDateTime.parse(parsedInput[2], formatter);
                        t = new Deadline(parsedInput[1], deadline);
                        this.tasklist.addTask(t);
                        break;
                    case EVENT:
                        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime start = LocalDateTime.parse(parsedInput[2], formatter1);
                        LocalDateTime end = LocalDateTime.parse(parsedInput[3], formatter1);
                        t = new Event(parsedInput[1], start, end);
                        this.tasklist.addTask(t);
                        break;
                    }
                    this.ui.showAddTask(t);
                    this.ui.showListSize(this.tasklist);
                    break;
                case MARK: {
                    int index = Integer.parseInt(parsedInput[1]);
                    Task task = this.tasklist.getTask(index);
                    task.markAsDone();
                    this.ui.showMarkedTask(task);
                    break;
                }
                case UNMARK: {
                    int index = Integer.parseInt(parsedInput[1]);
                    Task task = this.tasklist.getTask(index);
                    task.markAsUndone();
                    this.ui.showUnmarkedTask(task);
                    break;
                }
                case DELETE: {
                    int index = Integer.parseInt(parsedInput[1]);
                    Task task = this.tasklist.deleteTask(index);
                    this.ui.showDeletedTask(task);
                    this.ui.showListSize(this.tasklist);
                    break;
                }
                case FIND:
                    List<Task> filteredList = this.tasklist.getTasksWithKeyword(parsedInput[1]);
                    this.ui.showKeywordList(filteredList);
                    break;
                default:
                    throw new EchoException("I'm not sure I can do that");
                }
            } catch (EchoException | IllegalArgumentException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    public TaskList getTasklist() {
        return this.tasklist;
    }

    public UI getUi() {
        return this.ui;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public String getResponse(String input) {
        return "Echo heard: " + input;
    }
}
