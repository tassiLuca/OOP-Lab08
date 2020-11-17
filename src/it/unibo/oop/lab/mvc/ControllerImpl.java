package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the implementation of Controller interface.
 */
public class ControllerImpl implements Controller {
    /**
     * The history of strings printed.
     */
    private final List<String> history = new ArrayList<>();
    /**
     * The current position on history list.
     */
    private int currentPos;

    @Override
    public void setNextStringToPrint(final String str) {
        if (str == null) {
            throw new NullPointerException("str is null");
        }
        history.add(str);
    }

    @Override
    public String getNextStringToPrint() {
        return history.get(this.currentPos + 1);
    }

    @Override
    public List<String> getHistory() {
        return Collections.<String>unmodifiableList(history);
    }

    @Override
    public void printCurrentString() {
        if (history.get(this.currentPos) == null) {
            throw new IllegalStateException();
        }
        System.out.println(history.get(this.currentPos++));
    }
    
}
