package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * This is the implementation of Controller interface.
 */
public class ControllerImpl implements Controller {
    /**
     * The history of strings printed.
     */
    private final List<String> history = new ArrayList<>();
    /**
     * The next string to be printed.
     */
    private String nextString;

    @Override
    public void setNextStringToPrint(final String str) {
        this.nextString = Objects.requireNonNull(str);
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public List<String> getHistory() {
        return Collections.<String>unmodifiableList(history);
    }

    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException();
        }
        this.history.add(this.nextString);
        System.out.println(this.nextString);
    }

}
