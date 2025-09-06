package echo.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import echo.Echo;
import echo.task.Event;
import echo.task.Task;

public class EventCommand extends Command {
    private String instruction;
    private String from;
    private String to;

    public EventCommand(Echo echo, String instruction, String from, String to) {
        super(echo);
        this.instruction = instruction;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute() {
        StringBuilder msg = new StringBuilder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime start = LocalDateTime.parse(this.from, formatter);
        LocalDateTime end = LocalDateTime.parse(this.to, formatter);

        Task t = new Event(this.instruction, start, end);
        echo.getTasklist().addTask(t);

        msg.append(echo.getUi().showAddTask(t));
        msg.append(echo.getUi().showListSize(echo.getTasklist()));
        return msg.toString();
    }
}
