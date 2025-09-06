package echo;

import echo.command.Command;
import echo.echoexception.EchoException;
import echo.parser.Parser;
import echo.storage.Storage;
import echo.tasklist.TaskList;
import echo.ui.UI;

public class Echo {
    protected final Storage storage;
    protected final UI ui;
    protected final TaskList tasklist;

    public Echo() {
        this.storage = new Storage("data/echo.txt");
        this.ui = new UI();
        this.tasklist = new TaskList(this.storage.readFile());
    }

    public void run() {
        this.ui.showWelcome();

        while (true) {
            try {
                String input = this.ui.readCommand();
                Command command = Parser.parse(this, input);
                command.execute();
            } catch (EchoException | IllegalArgumentException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    public TaskList getTasklist() {
        return this.tasklist;
    }

    public UI getUi() {
        return this.ui;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public String getResponse(String input) {
        return "Echo heard: " + input;
    }
}
