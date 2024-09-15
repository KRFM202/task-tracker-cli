package com.github.krfm202.tasktracker.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static final String PATH = "src/main/resources/task-data.json";
    private static File dataFile;

    public FileManager() {
        dataFile = new File(PATH);
    }

    private void ensureFileExists() {
        try {
            dataFile.createNewFile();
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
