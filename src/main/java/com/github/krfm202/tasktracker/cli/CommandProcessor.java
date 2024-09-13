package com.github.krfm202.tasktracker.cli;

import java.util.List;

public class CommandProcessor {

    private final List<String> args;

    CommandProcessor(List<String> args) {
        this.args = args;
    }

    public void processAdd() {
        System.out.println(args);
        System.out.println("comando ejecutado add");
    }

    public void processDelete() {
        System.out.println(args);
        System.out.println("comando ejecutado delete");
    }

    public void processListAll() {
    }

    public void processExit() {
        System.exit(0);
    }
}
