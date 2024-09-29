package com.github.krfm202.tasktracker.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private final File dataFile;

    public FileManager() {
        String userHome = System.getProperty("user.home");
        String appDirectory = userHome + "/.task-tracker-data";
        dataFile = new File(appDirectory, "task-data.json");
        new File(appDirectory).mkdirs();
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            if (!dataFile.exists()) dataFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public String read() {
        ensureFileExists();
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(dataFile)) {
            while ((i = reader.read()) != -1) {
                stringBuilder.append((char) i);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error " + e.getMessage());
        }
        return stringBuilder.toString();
    }

    public void write(String data) {
        ensureFileExists();
        try (FileWriter writer = new FileWriter(dataFile)) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Error " + e.getMessage());
        }
    }
}
