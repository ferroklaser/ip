import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Echo {
    public static void main(String[] args) {
        String logo = "Echo";
        ArrayList<Task> list = new ArrayList<>();
        List<String> taskTypes = List.of("todo", "event", "deadline");
        System.out.println("Hello! I'm " + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String command = scanner.nextLine();
                String[] parts = command.split(" ", 2);
                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    int index = 1;
                    System.out.println("Let's take a look at the tasks in your list:");
                    for (Task task : list) {
                        System.out.println(index + "." + task);
                        index++;
                    }
                } else if (taskTypes.contains(parts[0])) {
                    Task t = null;
                    if (parts.length < 2 || parts[1].isEmpty()) {
                        throw new EchoException("Wait a min! Your description cannot be empty");
                    }
                    if (parts[0].equals("todo")) {
                        t = new Todo(parts[1]);
                        list.add(t);
                    } else if (parts[0].equals("deadline")) {
                        String[] taskParts = parts[1].split(" /by ");
                        if (taskParts.length < 2 || taskParts[1].isEmpty()) {
                            throw new EchoException("Wait a min! Your deadline cannot be empty");
                        }
                        t = new Deadline(taskParts[0], taskParts[1]);
                        list.add(t);
                    } else if (parts[0].equals("event")) {
                        String[] taskParts = parts[1].split(" /from ");
                        String[] fromToParts = taskParts[1].split(" /to ");
                        if (fromToParts[0].isEmpty() || fromToParts[1].isEmpty()) {
                            throw new EchoException("Wait a min! Your dates cannot be empty");
                        }
                        t = new Event(taskParts[0], fromToParts[0], fromToParts[1]);
                        list.add(t);
                    }
                    System.out.println("Ok, I've added this task for you");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                } else if (parts[0].equals("mark")) {
                    int index = Integer.parseInt(parts[1]);
                    Task task = list.get(index - 1);
                    task.markAsDone();
                    System.out.println("Good job! I've marked this task as done:");
                    System.out.println("  " + task);
                } else if (parts[0].equals("unmark")) {
                    int index = Integer.parseInt(parts[1]);
                    Task task = list.get(index - 1);
                    task.markAsUndone();
                    System.out.println("Alright, I've marked this task as not done for you:");
                    System.out.println("  " + task);
                } else {
                    throw new EchoException("I'm not sure I can do that");
                }
            } catch (EchoException error) {
                System.out.println(error.getMessage());
            }
        }
        System.out.println("Byeeee, cya!");
    }
}
