package echo.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import echo.Echo;
import echo.task.Deadline;
import echo.task.Task;

public class DeadlineCommand extends Command {
    private final String instruction;
    private final String by;

    public DeadlineCommand(Echo echo, String instruction, String by) {
        super(echo);
        this.instruction = instruction;
        this.by = by;
    }

    @Override
    public String execute() {
        StringBuilder msg = new StringBuilder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime deadline = LocalDateTime.parse(by, formatter);
        Task t = new Deadline(instruction, deadline);
        echo.getTasklist().addTask(t);

        msg.append(echo.getUi().showAddTask(t));
        msg.append(echo.getUi().showListSize(echo.getTasklist()));

        return msg.toString();
    };
}
