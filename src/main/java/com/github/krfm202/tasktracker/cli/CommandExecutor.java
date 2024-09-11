package com.github.krfm202.tasktracker.cli;

import java.util.ArrayList;

public class CommandExecutor {

    private static ArrayList<String> args;

    public static void setArgs(ArrayList<String> args) {
        CommandExecutor.args = args;
    }

    public static boolean add() {
        System.out.println(args);
        System.out.println("comando ejecutado add");
        return false;
    }

    public static boolean delete() {
        System.out.println(args);
        System.out.println("comando ejecutado delete");
        return false;
    }

    public static boolean listAll() {
        return false;
    }

    public static boolean exit() {
        System.exit(0);
        return false;
    }
}
