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

    public void processList(List<String> args) {
        List<String> taskList;
        if (args.isEmpty()) taskList = taskManager.list();
        else taskList = taskManager.list(args);

        System.out.println("Listing tasks:");
        taskList.forEach(System.out::println);
    }

    public void processExit() {
        System.exit(0);
    }
}
