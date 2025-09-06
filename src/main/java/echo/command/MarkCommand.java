package echo.command;

import echo.Echo;
import echo.task.Task;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(Echo echo, int index) {
        super(echo);
        this.index = index;
    }

    @Override
    public String execute() {
        Task task = echo.getTasklist().getTask(this.index);
        task.markAsDone();
        return echo.getUi().showMarkedTask(task);
    }
}
