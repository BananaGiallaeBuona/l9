package it.unibo.mvc;

/**
 * Defines the basic contract for a controller capable of producing an output.
 * The specific implementation decides what and how to print.
 */
@FunctionalInterface
public interface Controller {
    /**
     * Prints the content on the standard output.
     */
    void print();
}
