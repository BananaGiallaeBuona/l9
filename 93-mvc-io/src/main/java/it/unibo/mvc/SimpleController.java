package it.unibo.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of a simple controller.
 */
public final class SimpleController implements Controller {
    private final List<String> history;
    private String nextString;

    /**
     * Builds a new instance of SimpleController.
     */
    public SimpleController() {
        history = new ArrayList<>();
    }

    @Override
    public void setNextStringToPrint(final String nxtString) {
        this.nextString = Objects.requireNonNull(nxtString, "The string cannot be null");
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public List<String> getPrintedStringsHistory() {
        return Collections.unmodifiableList(new ArrayList<>(this.history));
    }

    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("There isn't current string");
        }
        System.out.println(this.nextString); //NOPMD
        history.add(this.nextString);
    }
}
