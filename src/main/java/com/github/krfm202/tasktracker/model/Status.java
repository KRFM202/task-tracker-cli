package com.github.krfm202.tasktracker.model;

public enum Status {
    TODO(1, "To do"), IN_PROGRESS(2, "In progress"), DONE(3, "Done");

    Status(int id, String description) {
    }
}
