package echo.command;

import echo.Echo;
import echo.task.Task;
import echo.task.Todo;

public class ToDoCommand extends Command {
    private final String instruction;

    public ToDoCommand(Echo echo, String instruction) {
        super(echo);
        this.instruction = instruction;
    }

    @Override
    public String execute() {
        StringBuilder msg = new StringBuilder();

        Task t = new Todo(this.instruction);
        echo.getTasklist().addTask(t);

        msg.append(echo.getUi().showAddTask(t));
        msg.append(echo.getUi().showListSize(echo.getTasklist()));
        return msg.toString();
    }
}
