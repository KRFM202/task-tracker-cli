package com.github.krfm202.tasktracker.model;

public enum Status {
    TODO(1, "To do"), IN_PROGRESS(2, "In progress"), DONE(3, "Done");

    private final int id;
    private final String description;

    Status(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
