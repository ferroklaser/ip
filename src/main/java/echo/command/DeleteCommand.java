package echo.command;

import echo.Echo;
import echo.task.Task;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(Echo echo, int index) {
        super(echo);
        this.index = index;
    }

    @Override
    public String execute() {
        Task task = echo.getTasklist().deleteTask(this.index);

        StringBuilder msg = new StringBuilder();
        msg.append(echo.getUi().showDeletedTask(task));
        msg.append(echo.getUi().showListSize(echo.getTasklist()));
        return msg.toString();
    }
}
