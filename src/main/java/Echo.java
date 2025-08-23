import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

enum Command {
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    LIST,
    DELETE,
    BYE,
    DEFAULT
}

public class Echo {
    public static void main(String[] args) {
        String logo = "Echo";
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm " + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(" ", 2);
                Command command;
               try {
                   command = Command.valueOf(parts[0].toUpperCase());
               } catch (IllegalArgumentException e) {
                   command = Command.DEFAULT;
               }
                switch (command) {
                case BYE:
                    System.out.println("Byeeee, cya!");
                    return;
                case LIST: {
                    int index = 1;
                    System.out.println("Let's take a look at the tasks in your list:");
                    for (Task task : list) {
                        System.out.println(index + "." + task);
                        index++;
                    }
                    break;
                }
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task t = null;
                    if (parts.length < 2 || parts[1].isEmpty()) {
                        throw new EchoException("Wait a min! Your description cannot be empty");
                    }
                    switch (command) {
                        case TODO:
                            t = new Todo(parts[1]);
                            list.add(t);
                            break;
                        case DEADLINE:
                            String[] deadlineParts = parts[1].split(" /by ");
                            if (deadlineParts.length < 2 || deadlineParts[1].isEmpty()) {
                                throw new EchoException("Wait a min! Your deadline cannot be empty");
                            }
                            t = new Deadline(deadlineParts[0], deadlineParts[1]);
                            list.add(t);
                            break;
                        case EVENT:
                            String[] eventParts = parts[1].split(" /from ");
                            String[] fromToParts = eventParts[1].split(" /to ");
                            if (fromToParts[0].isEmpty() || fromToParts[1].isEmpty()) {
                                throw new EchoException("Wait a min! Your dates cannot be empty");
                            }
                            t = new Event(eventParts[0], fromToParts[0], fromToParts[1]);
                            list.add(t);
                            break;
                    }
                    System.out.println("Ok, I've added this task for you");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                case MARK: {
                    int index = Integer.parseInt(parts[1]);
                    Task task = list.get(index - 1);
                    task.markAsDone();
                    System.out.println("Good job! I've marked this task as done:");
                    System.out.println("  " + task);
                    break;
                }
                case UNMARK: {
                    int index = Integer.parseInt(parts[1]);
                    Task task = list.get(index - 1);
                    task.markAsUndone();
                    System.out.println("Fine, I'll unmark this task for you:");
                    System.out.println("  " + task);
                    break;
                }
                case DELETE: {
                    int index = Integer.parseInt(parts[1]);
                    Task task = list.remove(index - 1);
                    System.out.println("Task has been removed");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                }
                default:
                    throw new EchoException("I'm not sure I can do that");
                }
            } catch (EchoException | IllegalArgumentException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
