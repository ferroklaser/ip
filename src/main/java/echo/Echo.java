package echo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public static void main(String[] args) {
        Storage storage = new Storage("data/echo.txt");
        UI ui = new UI();
        TaskList list = new TaskList(storage.readFile());
        ui.showWelcome();

        while (true) {
            try {
                String input = ui.readCommand();
                String[] parsedInput = Parser.parse(input);
                Command command = Command.valueOf(parsedInput[0]);
                switch (command) {
                case BYE:
                    ui.showExit();
                    storage.saveFile(list.getList());
                    return;
                case LIST: {
                    ui.showList(list);
                    break;
                }
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task t = null;
                    switch (command) {
                    case TODO:
                        t = new Todo(parsedInput[1]);
                        list.addTask(t);
                        break;
                    case DEADLINE:
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime deadline = LocalDateTime.parse(parsedInput[2], formatter);
                        t = new Deadline(parsedInput[1], deadline);
                        list.addTask(t);
                        break;
                    case EVENT:
                        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime start = LocalDateTime.parse(parsedInput[2], formatter1);
                        LocalDateTime end = LocalDateTime.parse(parsedInput[3], formatter1);
                        t = new Event(parsedInput[1], start, end);
                        list.addTask(t);
                        break;
                    }
                    ui.showAddTask(t);
                    ui.showListSize(list);
                    break;
                case MARK: {
                    int index = Integer.parseInt(parsedInput[1]);
                    Task task = list.getTask(index);
                    task.markAsDone();
                    ui.showMarkedTask(task);
                    break;
                }
                case UNMARK: {
                    int index = Integer.parseInt(parsedInput[1]);
                    Task task = list.getTask(index);
                    task.markAsUndone();
                    ui.showUnmarkedTask(task);
                    break;
                }
                case DELETE: {
                    int index = Integer.parseInt(parsedInput[1]);
                    Task task = list.deleteTask(index);
                    ui.showDeletedTask(task);
                    ui.showListSize(list);
                    break;
                }
                default:
                    throw new EchoException("I'm not sure I can do that");
                }
            } catch (EchoException | IllegalArgumentException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
