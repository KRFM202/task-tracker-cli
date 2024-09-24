package com.github.krfm202.tasktracker.manager;

import com.github.krfm202.tasktracker.exceptions.CommandException;
import com.github.krfm202.tasktracker.model.Task;
import com.github.krfm202.tasktracker.model.Status;
import com.github.krfm202.tasktracker.storage.FileManager;
import com.github.krfm202.tasktracker.storage.JsonParser;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;


public class TaskManager {
    FileManager file;
    JsonParser parser;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

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
            List<Task> taskList = parser.parseJsonStringToList(file.read());
            taskList.add(task);
            file.write(parser.generateJsonString(taskList));
        } else {
            System.out.println(args);
            throw new CommandException("Insufficient args, try again");
        }
        return task.getId();
    }

    private final Function<Task, String> mapFormat = t ->
            "ID: " + t.getId() +
                    "\nDESCRIPTION: " + t.getDescription() +
                    "\nSTATUS: " + t.getStatus().getDescription() +
                    "\nUPDATED AT: " + t.getUpdatedAt().format(formatter) +
                    "\nCREATED AT: " + t.getUpdatedAt().format(formatter)
                    + "\n";

    public List<String> list(List<String> args) {
        List<String> taskList;
        taskList = parser.parseJsonStringToList(file.read())
                .stream()
                .filter(t -> t.getStatus().getId() == findMatchingStatus(args.get(0)).getId())
                .sorted(Comparator.comparingInt(Task::getId))
                .map(mapFormat)
                .toList();
        return taskList;
    }

    public List<String> list() {
        List<String> taskList;
        taskList = parser.parseJsonStringToList(file.read())
                .stream()
                .sorted(Comparator.comparingInt(Task::getId))
                .map(mapFormat)
                .toList();
        return taskList;
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
