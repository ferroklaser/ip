package echo;

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

    public void printList() {
        int index = 1;
        for (Task task : this.taskList) {
            System.out.println(index + "." + task);
            index++;
        }
    }
}
