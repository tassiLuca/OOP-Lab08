package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Objects;

public final class MatchLogView implements DrawNumberView {

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String LOG_FILE_PATH = System.getProperty("user.dir") 
            + SEPARATOR + "res" + SEPARATOR;

    private final PrintStream outputLog;

    /**
     * Creates a new PrintStream from a filename in the res folder of the project.
     * @param fileName
     *              the name of the log file
     * @throws FileNotFoundException
     */
    public MatchLogView(final String fileName) throws FileNotFoundException {
        this(new PrintStream(new File(LOG_FILE_PATH
                + Objects.requireNonNull(fileName))));
    }

    /**
     * @param outputLog the PrintStream where to write the match logs
     */
    public MatchLogView(final PrintStream outputLog) {
        super();
        this.outputLog = outputLog;
        this.outputLog.println("*** This is the match log file ***");
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) { }

    @Override
    public void start() { }

    @Override
    public void numberIncorrect() {
        this.outputLog.println(new Date(System.currentTimeMillis()) 
                + ": Incorrect Number");
    }

    @Override
    public void result(final DrawResult res) {
        this.outputLog.println(new Date(System.currentTimeMillis()) 
                + ": " + res);
    }

    @Override
    public void limitsReached() {
        this.outputLog.println(new Date(System.currentTimeMillis()) 
                + ": You lost!");
    }

    @Override
    public void displayError(final String message) {
        this.outputLog.println(new Date(System.currentTimeMillis()) 
                + ": [ERROR] " + message);
    }

}
