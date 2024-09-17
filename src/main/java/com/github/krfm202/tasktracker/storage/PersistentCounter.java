package com.github.krfm202.tasktracker.storage;

import java.io.*;

public class PersistentCounter implements Serializable {
    private static final String PATH = "src/main/resources/counter.ser";
    @Serial
    private static final long serialVersionUID = 1L;
    private int counter;

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

    public static PersistentCounter loadState() throws IOException {
        File file = new File(PATH);
        if (file.createNewFile()) {
            return new PersistentCounter();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(PATH))) {
            return (PersistentCounter) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e);
            return new PersistentCounter();
        }
    }
}
