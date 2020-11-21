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
    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
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
     * @return the pathname string of the current file.
     */
    public String getFilePath() {
        return this.file.toString();
    }

    /**
     * 
     * @param strToBeSaved
     * @throws IOException 
     */
    public void saveString(final String strToBeSaved) throws IOException {
        try (BufferedWriter w = new BufferedWriter(
                new FileWriter(this.file))
        ) {
            w.write(strToBeSaved);
            w.newLine();
        }
    }

}
