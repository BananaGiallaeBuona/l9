package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;
/*
TRASFORMO IN: current è l'ultima dentro strings, next è next e viene resa current venendo
 aggiunta quando la si stampa, se next viene chiamata next prioma di essere sostituita 
 allora viene cambiata senza problemi
*/
/**
 * 
 *
 */

public final class SimpleController implements Controller {
    private String nextString;
    private final List<String> strings;

    /**
     * Builds a new instance of SimpleController.
     */
    SimpleController() {
        strings = new ArrayList<>();
    }

    /**
     * Sets the next string to print.
     * 
     * @param s the string to print
     * 
     * @throws IllegalAccessException if the string is null
     */
    public void setNextString(final String s) throws IllegalAccessException {
        if (s != null) {
            nextString = s;
        } else {
            throw new IllegalAccessException("the string is null!!");
        }
    }

    /**
     * Prints the current string to the standard output.
     * * @throws IllegalStateException if the current string is unset
     */
    @Override
    public void print() {
        if (this.strings != null) {
            System.out.println(strings.getLast()); //NOPMD
        } else {
            throw new IllegalStateException("there is no current");
        }
    }

    /**
     * Gets the next string to print.
     * 
     * @return the next string
     */
    public String getNexString() {
        if (this.strings != null) {
            return this.nextString;
        } else {
            throw new IllegalStateException("there ins't already a next string");
        }
    }

    /**
     * Gets the history of printed strings.
     *
     *  @return the list of printed strings
     */
    public List<String> getHIstory() {
        if (this.strings != null) {
            return this.strings;
        } else {
            throw new IllegalStateException("there is no history");
        }
    }
}
