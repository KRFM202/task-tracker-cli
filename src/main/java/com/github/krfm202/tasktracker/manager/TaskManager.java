package com.github.krfm202.tasktracker.manager;

import com.github.krfm202.tasktracker.model.Task;
import com.github.krfm202.tasktracker.model.TaskStatus;

public class TaskManager {

    private Task task;

    public TaskManager() {

    }

    public void add(String description) {
        task = new Task();
        task.setDescription(description);
        task.setStatus(TaskStatus.TODO);
    }


}
