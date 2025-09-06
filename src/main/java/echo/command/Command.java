package echo.command;

import echo.Echo;

public abstract class Command {
    protected Echo echo;

    public Command(Echo echo) {
        this.echo = echo;
    }

    public abstract String execute();
}
