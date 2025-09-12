package echo.parser;

import echo.Echo;
import echo.command.ByeCommand;
import echo.command.Command;
import echo.command.DeadlineCommand;
import echo.command.DeleteCommand;
import echo.command.EventCommand;
import echo.command.FindCommand;
import echo.command.ListCommand;
import echo.command.MarkCommand;
import echo.command.ToDoCommand;
import echo.command.UnmarkCommand;
import echo.echoexception.EchoException;

enum Action {
    BYE,
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    DELETE,
    FIND,
    DEFAULT
}

/**
 * Represents parser to interpret raw input strings to commands.
 */
public class Parser {
    /**
     * @param input Input string to be parsed.
     * @return array A String array representing parsed commands and arguments.
     * @throws EchoException if description, date or time are missing for task creation.
     */
    public static Command parse(Echo echo, String input) throws EchoException {
        String[] parts = input.split(" ", 2);
        Action action;
        try {
            action = Action.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            action = Action.DEFAULT;
        }
        switch (action) {
        case BYE:
            return new ByeCommand(echo);
        case LIST:
            return new ListCommand(echo);
        case TODO, DEADLINE, EVENT:
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new EchoException("Wait a min! Your description cannot be empty!");
            }
            switch (action) {
            case TODO:
                return new ToDoCommand(echo, parts[1]);
            case DEADLINE:
                String[] deadlineParts = parts[1].split(" /by ");
                if (deadlineParts.length < 2 || deadlineParts[1].isEmpty()) {
                    throw new EchoException("Wait a min! Your deadline cannot be empty!");
                }
                return new DeadlineCommand(echo, deadlineParts[0], deadlineParts[1]);
            case EVENT:
                String[] eventParts = parts[1].split(" /from ");
                String[] fromToParts = eventParts[1].split(" /to ");
                if (fromToParts[0].isEmpty() || fromToParts[1].isEmpty()) {
                    throw new EchoException("Wait a min! Your to and from cannot be empty");
                }
                return new EventCommand(echo, eventParts[0], fromToParts[0], fromToParts[1]);
            }
        case MARK:
            int markIndex = Integer.parseInt(parts[1]);
            assert markIndex > 0: "index of item to mark must be greater than 0";
            return new MarkCommand(echo, markIndex);
        case UNMARK:
            int unmarkIndex = Integer.parseInt(parts[1]);
            assert unmarkIndex > 0: "index of item to unmark must be greater than 0";
            return new UnmarkCommand(echo, unmarkIndex);
        case DELETE:
            int deleteIndex = Integer.parseInt(parts[1]);
            assert deleteIndex > 0: "index of item to delete must be greater than 0";
            return new DeleteCommand(echo, deleteIndex);
        case FIND:
            return new FindCommand(echo,parts[1]);
        default:
            return null;
        }
    }
}
