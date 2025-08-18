import java.util.Scanner;
import java.util.ArrayList;

public class Echo {
    public static void main(String[] args) {
        String logo = "Echo";
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm " + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("What can I do for you?");

        while (true) {
            String command = scanner.nextLine();
            String[] words = command.split(" ");
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                int index = 1;
                System.out.println("Let's take a look at the tasks in your list:");
                for (Task task : list) {
                    System.out.println(index + "." + task);
                    index++;
                }
            } else if (words[0].equals("mark")) {
                int index = Integer.parseInt(words[1]);
                Task task = list.get(index - 1);
                task.markAsDone();
                System.out.println("Good job! I've marked this task as done:");
                System.out.println("  " + task);
            } else if (words[0].equals("unmark")) {
                int index = Integer.parseInt(words[1]);
                Task task = list.get(index - 1);
                task.markAsUndone();
                System.out.println("Alright, I've marked this task as not done for you:");
                System.out.println("  " + task);
            } else {
                Task t = new Task(command);
                list.add(t);
                System.out.println("added: " + command);
            }
        }
        System.out.println("Byeeee, cya!");
    }
}
