package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * Application controller. Performs the I/O.
 */
public final class Controller {
    private File file;
    private static final String SEP = System.getProperty("file.separator");
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final File DEFAULT_FILE =
        new File(PROJECT_ROOT + SEP + "build" + SEP + "texts" + SEP + "output.txt");

    Controller() {
        this.file = DEFAULT_FILE;
        this.file.getParentFile().mkdirs(); //this creates al the directory that doesn't
                                            //already exists
    }

    /**
     * Sets the current file used for I/O operations.
     *
     * @param file the file to use
     */
    public void setFile(final File file) {
        this.file = file;
    }

    /**
     * gets the current file used for I/O operations.
     *
     * @return  this.file 
     */
    public File getFile() {
        return file;
    }

    /**
     * gets the path of the file used for I/O operations.
     *
     * @return  the path of the file used for I/O operations.
     */
    public Path getFilePath() {
        return this.file.toPath();
    }

    /**
     * Writes the given content to the current file.
     * The content replaces any existing data in the file.
     *
     * @param content the text to write
     * @throws IOException if an I/O error occurs while writing
     */
    public void write(final String content) throws IOException {
        try (PrintStream ps = new PrintStream(this.file, StandardCharsets.UTF_8)) {
            ps.print(content);
        }
    }

}
