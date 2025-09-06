package echo.command;

import echo.Echo;
import echo.task.Task;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(Echo echo, int index) {
        super(echo);
        this.index = index;
    }

    @Override
    public String execute() {
        Task task = echo.getTasklist().getTask(this.index);
        task.markAsUndone();
        return echo.getUi().showUnmarkedTask(task);
    }
}
