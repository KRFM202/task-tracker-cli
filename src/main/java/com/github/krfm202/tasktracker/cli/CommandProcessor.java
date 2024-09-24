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

    public void processDelete(List<String> args) {
        int id = taskManager.delete(args);
        if (id != -1) System.out.println("Task deleted successfully (ID: " + id + ")");
        else System.out.println("Task not deleted, try again");
    }

    public void processMark(List<String> args) {
        if (taskManager.mark(args)) System.out.println("Task status successfully changed");
        else System.out.println("Task status was not changed");
    }

    public void processUpdate(List<String> args) {
        int id = taskManager.update(args);
        if (id != -1) System.out.println("Task successfully modified (ID: " + id + ")");
        else System.out.println("Task failed to update, try again");
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
