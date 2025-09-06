package echo.command;

import echo.Echo;

public class ListCommand extends Command {
    public ListCommand(Echo echo) {
        super(echo);
    }

    @Override
    public String execute() {
        return echo.getUi().showList(this.echo.getTasklist()) ;
    }
}
