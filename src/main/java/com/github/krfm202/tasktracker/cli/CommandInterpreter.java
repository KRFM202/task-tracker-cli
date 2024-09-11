package com.github.krfm202.tasktracker.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandInterpreter {
    public static void main(String[] args) {
        while (true) {
            ArrayList<String> splitLine = lineSplitter(consoleReader());
            String command = splitLine.remove(0);
            CommandExecutor.setArgs(splitLine);
            var isAccepted = false;
            switch (command) {
                case "add" -> isAccepted = CommandExecutor.add();
                case "delete" -> isAccepted = CommandExecutor.delete();
                case "list" -> isAccepted = CommandExecutor.listAll();
                case "exit" -> isAccepted = CommandExecutor.exit();
            }
        }
    }

    public static String consoleReader() {
        BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = "";
        try {
            System.out.println("Escribir:");
            inputLine = inputBuffer.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputLine;
    }

    public static ArrayList<String> lineSplitter(String text) {
        ArrayList<String> splitText = new ArrayList<>();
        String regex = "\\b\\w+\\b|\"(?:[^\"\\\\]|\\\\.)*\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            splitText.add(matcher.group());
        }
        System.out.println("text partido:");
        System.out.println(splitText);
        return splitText;
    }
}
