package echo.tasklist;

import echo.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<? extends Task> taskList) {
       this.taskList = new ArrayList<>(taskList);
    }

    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public Task deleteTask(int index) {
        return this.taskList.remove(index - 1);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    public List<Task> getList() {
        return this.taskList;
    }

    public List<Task> getTasksWithKeyword(String keyword) {
        return this.taskList.stream().filter(task -> task.hasKeyword(keyword)).toList();
    }

    public static void printList(List<Task> list) {
        int index = 1;
        for (Task task : list) {
            System.out.println(index + "." + task);
            index++;
        }
    }
}
