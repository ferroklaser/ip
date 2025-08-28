package echo.parser;

import echo.echoexception.EchoException;
import echo.command.Command;

public class Parser {
    public static String[] parse(String input) throws EchoException {
        String[] parts = input.split(" ", 2);
        Command command;
        try {
            command = Command.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            command = Command.DEFAULT;
        }
        switch (command) {
        case BYE:
            return new String[]{"BYE"};
        case LIST:
            return new String[]{"LIST"};
        case TODO:
        case DEADLINE:
        case EVENT:
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new EchoException("Wait a min! Your description cannot be empty!");
            }
            switch (command) {
            case TODO:
                return new String[]{"TODO", parts[1]};
            case DEADLINE:
                String[] deadlineParts = parts[1].split(" /by ");
                if (deadlineParts.length < 2 || deadlineParts[1].isEmpty()) {
                    throw new EchoException("Wait a min! Your deadline cannot be empty!");
                }
                return new String[]{ "DEADLINE", deadlineParts[0], deadlineParts[1]};
            case EVENT:
                String[] eventParts = parts[1].split(" /from ");
                String[] fromToParts = eventParts[1].split(" /to ");
                if (fromToParts[0].isEmpty() || fromToParts[1].isEmpty()) {
                    throw new EchoException("Wait a min! Your to and from cannot be empty");
                }
                return new String[]{"EVENT", eventParts[0], fromToParts[0], fromToParts[1]};
            }
        case MARK:
            return new String[]{"MARK", parts[1]};
        case UNMARK:
            return new String[]{"UNMARK", parts[1]};
        case DELETE:
            return new String[]{"DELETE", parts[1]};
        default:
            return new String[]{"DEFAULT"};
        }
    }
}
