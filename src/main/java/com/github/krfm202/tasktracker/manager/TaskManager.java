package com.github.krfm202.tasktracker.manager;

import com.github.krfm202.tasktracker.exceptions.CommandException;
import com.github.krfm202.tasktracker.model.Task;
import com.github.krfm202.tasktracker.model.Status;
import com.github.krfm202.tasktracker.storage.FileManager;
import com.github.krfm202.tasktracker.storage.JsonParser;

import java.util.List;


public class TaskManager {
    private static List<Task> taskList;
    FileManager file;
    JsonParser parser;

    public TaskManager() {
        file = new FileManager();
        parser = new JsonParser();
    }

    public int add(List<String> args) {
        String description;
        Status status;
        Task task;
        if (!args.isEmpty() && args.size() <= 2) {
            description = cleanDescription(args.get(0));
            if (args.size() == 1) status = Status.TODO;
            else status = findMatchingStatus(args.get(1));
            task = new Task(description, status);
            taskList = parser.parseJsonStringToList(file.read());
            taskList.add(task);
            file.write(parser.generateJsonString(taskList));
        } else {
            System.out.println(args);
            throw new CommandException("Insufficient Args, try again");
        }
        return task.getId();
    }

    private Status findMatchingStatus(String sts) {
        Status status;
        switch (sts) {
            case "inprogress" -> status = Status.IN_PROGRESS;
            case "done" -> status = Status.DONE;
            case "todo" -> status = Status.TODO;
            default -> {
                status = Status.TODO;
                System.out.println("Unexpected value: " + sts + ", assigned status: to-do by default");
            }
        }
        return status;
    }

    private String cleanDescription(String description) {
        return description.replace("\"", "");
    }
}
