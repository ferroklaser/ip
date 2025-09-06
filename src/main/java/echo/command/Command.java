package echo.command;

import echo.Echo;

public class Command {
    protected Echo echo;

    public Command(Echo echo) {
        this.echo = echo;
    }

    public String execute() {
        return "";
    };
}
