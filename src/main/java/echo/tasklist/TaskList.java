package echo.tasklist;

import echo.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task list. A <code>TaskList</code> object stores the list of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList(List<? extends Task> taskList) {
       this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Adds a <code>Task</code> into the stored list of tasks.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Removes task with the corresponding index - 1from the stored
     * list of tasks and returns it.
     *
     * @param index Position of task to be removed from the list, indexed at 1.
     * @return Task that was removed from the list.
     */
    public Task deleteTask(int index) {
        return this.taskList.remove(index - 1);
    }

    /**
     * Returns number of items in list of task.
     *
     * @return size Number of items in list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns the task with the corresponding index - 1
     *
     * @param index Position of task to be returned from the list, indexed at 1.
     * @return Task at the corresponding index
     */
    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Prints every task in the list.
     */
    public void printList() {
        int index = 1;
        for (Task task : this.taskList) {
            System.out.println(index + "." + task);
            index++;
        }
    }
}
