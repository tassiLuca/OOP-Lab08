package it.unibo.oop.lab.mvc;

import java.util.List;

/**
 * A controller that prints strings and has memory of the strings it printed.
 */
public interface Controller {
    /**
     * This method sets the next string to print. 
     * @param str 
     *          the next string to be printed
     */
    void setNextStringToPrint(String str);

    /**
     * @return the next string to print
     */
    String getNextStringToPrint();

    /**
     * Get the history of the printed strings. 
     * @return List<String>
     *          the list of printed strings
     */
    List<String> getHistory();

    /** 
     * Prints the current string.
     */
    void printCurrentString();
}
