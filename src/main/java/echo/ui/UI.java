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
    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello, I'm Echo.");
        System.out.println("What can I do for you today?");
    }

    public void showExit() {
        System.out.println("Byeeee, cya!");
    }

    public void showList(TaskList list) {
        System.out.println("Let's take a look at the tasks in your list:");
        TaskList.printList(list.getList());
    }

    public void showKeywordList(List<Task> list) {
        System.out.println("Here are the matching tasks in your list:");
        TaskList.printList(list);
    }

    public void showMarkedTask(Task task) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("Fine, I'll unmark this task for you:");
        System.out.println("  " + task);
    }

    public void showDeletedTask(Task task) {
        System.out.println("Task has been removed");
        System.out.println("  " + task);
    }

    public void showAddTask(Task task) {
        System.out.println("Ok, I've added this task for you");
        System.out.println("  " + task);
    }

    public void showListSize(TaskList taskList) {
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

}
