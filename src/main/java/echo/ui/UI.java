package echo.ui;

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

    public String showExit() {
        return "Echo Echo...going back into the Omnitrix! See you soon!\n";
    }

    public String showList(TaskList list) {
        String msg = TaskList.printList(list);
        msg = "Mission briefing! Here are your Alien tasks Ben:\n" + msg + "\n";
        return msg;
    }

    public String showKeywordList(TaskList list) {
        String msg = TaskList.printList(list);
        msg = "Incoming! Matching aliens detected on your list:\n" + msg + "\n";
        return msg;
    }

    public String showMarkedTask(Task task) {
        StringBuilder msg = new StringBuilder();
        msg.append("Boom! Mission Accomplished! Alien task contained!:\n");
        msg.append("  ").append(task).append("\n");
        return msg.toString();
    }

    public String showUnmarkedTask(Task task) {
        StringBuilder msg = new StringBuilder();
        msg.append("Uh-oh! Uh-oh! Mission reset! Alien task has escaped!:\n");
        msg.append("  ").append(task).append("\n");
        return msg.toString();
    }

    public String showDeletedTask(Task task) {
        StringBuilder msg = new StringBuilder();
        msg.append("Destroying Alien task....AHHHHHH!\n");
        msg.append("  ").append(task).append("\n");
        return msg.toString();
    }

    public String showAddTask(Task task) {
        return "Alien task added! Alien task added!\n" + task + "\n";
    }

    public String showListSize(TaskList taskList) {
        return "Now you have " + taskList.getSize() + " Alien tasks to contain.\n";
    }

}
