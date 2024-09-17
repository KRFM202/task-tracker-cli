package com.github.krfm202.tasktracker.cli;

import com.github.krfm202.tasktracker.manager.TaskManager;

import java.util.List;

public class CommandProcessor {
    private static TaskManager taskManager;

    CommandProcessor() {
        taskManager = new TaskManager();
    }

    public void processAdd(List<String> args) {
        int id = taskManager.add(args);
        System.out.println("Task added successfully (ID: " + id + ")");
    }

    public void processDelete() {
        System.out.println();
        System.out.println("comando ejecutado delete");
    }

    public void processListAll() {
    }

    public void processExit() {
        System.exit(0);
    }
}
