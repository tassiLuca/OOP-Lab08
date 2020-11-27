package it.unibo.oop.lab.mvcio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Simple controller responsible of I/O access. It considers a single
 * file at a time, and it is able to serialize objects int it.
 */
public class Controller {

    private static final String DEFAULT_PATH = System.getProperty("user.home") 
            + System.getProperty("file.separator") + "output.txt";
    private File file;

    /**
     * Creates a new file with the default path file name.
     */
    public Controller() {
        this.file = new File(DEFAULT_PATH);
    }

    /**
     * Sets a File as the current File.
     * @param file
     *          the file where to write
     */
    public void setFile(final File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            this.file = file;
        } else {
            throw new IllegalArgumentException("The folder doesn't exixts.");
        }
    }

    /**
     * @return the current file.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * 
     * @return the pathname string of the current file
     */
    public String getFilePath() {
        return this.file.toString();
    }

    /**
     * 
     * @param text
     *          the text to save
     * @throws IOException 
     *          if the writing fails
     */
    public void saveString(final String text) throws IOException {
        try (BufferedWriter w = new BufferedWriter(
                new FileWriter(this.file))
        ) {
            w.write(text);
            w.newLine();
        }
    }

}
