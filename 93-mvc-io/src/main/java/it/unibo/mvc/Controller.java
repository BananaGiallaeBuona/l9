package it.unibo.mvc;

import java.util.List;

/**
 * Defines the basic contract for a controller capable of producing an output.
 */
public interface Controller {

    /**
     * Sets the next string to print.
     * 
     * @param nextString the string to set
     * 
     * @throws IllegalArgumentException if the string is null
     */
    void setNextStringToPrint(String nextString);

    /**
     * Gets the next string to print.
     * 
     * @return the next string
     */
    String getNextStringToPrint();

    /**
     * Gets the history of printed strings.
     * 
     * @return the list of printed strings
     */
    List<String> getPrintedStringsHistory();

    /**
     * Prints the current string on the standard output.
     * 
     * @throws IllegalStateException if the current string is unset
     */
    void printCurrentString();
}
