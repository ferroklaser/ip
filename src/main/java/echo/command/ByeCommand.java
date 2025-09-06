package echo.command;

import echo.Echo;

public class ByeCommand extends Command {
    public ByeCommand(Echo echo) {
        super(echo);
    }

    @Override
    public String execute() {
        this.echo.getStorage().saveFile(echo.getTasklist().getList());
        return echo.getUi().showExit();
    }
}
