package echo.ui;

import java.util.List;
import java.util.Scanner;

import echo.task.Task;
import echo.tasklist.TaskList;


/**
 * Represents the UI that users will interact with. A <code>UI</code> object
 * stores a <code>Scanner</code> scanner.
 */
public class UI {
    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello, I'm Echo Echo.");
        System.out.println("What can I do for you today?");
    }

    public String showExit() {
        return "Echo Echo...going back into the Omnitrix! See you soon!\n";
    }

    public String showList(TaskList list) {
        String msg = TaskList.printList(list);
        msg = "Let's take a look at the tasks in your list:\n" + msg + "\n";
        return msg;
    }

    public String showKeywordList(TaskList list) {
        String msg = TaskList.printList(list);
        msg = "Here are the matching tasks in your list:\n" + msg + "\n";
        return msg;
    }

    public String showMarkedTask(Task task) {
        StringBuilder msg = new StringBuilder();
        msg.append("Good job! I've marked this task as done:\n");
        msg.append("  ").append(task).append("\n");
        return msg.toString();
    }

    public String showUnmarkedTask(Task task) {
        StringBuilder msg = new StringBuilder();
        msg.append("Fine, I'll unmark this task for you:\n");
        msg.append("  ").append(task).append("\n");
        return msg.toString();
    }

    public String showDeletedTask(Task task) {
        StringBuilder msg = new StringBuilder();
        msg.append("Destroying task....AHHHHHH!\n");
        msg.append("  ").append(task).append("\n");
        return msg.toString();
    }

    public String showAddTask(Task task) {
        return "Task Added! Task Added!\n" + task + "\n";
    }

    public String showListSize(TaskList taskList) {
        return "Now you have " + taskList.getSize() + " tasks in your list.\n";
    }

}
