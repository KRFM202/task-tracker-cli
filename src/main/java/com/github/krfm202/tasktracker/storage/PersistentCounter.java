package com.github.krfm202.tasktracker.storage;

import java.io.*;

public class PersistentCounter implements Serializable {
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String APP_DIRECTORY = USER_HOME + "/.task-tracker-data";
    private static final String PATH = APP_DIRECTORY + "/counter.ser";

    @Serial
    private static final long serialVersionUID = 1L;
    private int counter;

    public PersistentCounter() {
        ensureFileExist();
    }

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PATH))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static PersistentCounter loadState() {
        File file = new File(PATH);
        if (!file.exists()) return new PersistentCounter();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(PATH))) {
            return (PersistentCounter) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e);
            return new PersistentCounter();
        }
    }

    private void ensureFileExist() {
        new File(APP_DIRECTORY).mkdirs();
        File file = new File(PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Error: " + e);
            }
        }
    }
}
