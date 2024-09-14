package com.github.krfm202.tasktracker;

import com.github.krfm202.tasktracker.cli.CommandInterpreter;

public class Application {
    public static void main(String[] args) {
        CommandInterpreter commandInterpreter = new CommandInterpreter();
        commandInterpreter.run();
    }
}
