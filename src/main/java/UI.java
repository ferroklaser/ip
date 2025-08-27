import java.util.Scanner;

public class UI {
    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello, I'm Echo");
        System.out.println("What can I do for you today?");
    }

    public void showExit() {
        System.out.println("Byeeee, cya!");
    }

    public void showList(TaskList list) {
        System.out.println("Let's take a look at the tasks in your list:");
        list.printList();
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
