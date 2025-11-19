package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private static Controller controller = new Controller();
    private static final int PROPORTION = 5;
    private static final String TITLE = "EX 92";
    private final JFrame frame = new JFrame(TITLE);
    private final JTextArea text;
    private final JButton saveButton;

    SimpleGUI() {
        final JPanel canvas = new JPanel(new BorderLayout());
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.text = new JTextArea("write here");
        //controller.setFile(this.text);
        canvas.add(text, BorderLayout.CENTER);
        this.saveButton = new JButton("save");
        canvas.add(saveButton, BorderLayout.LINE_END);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                try { 
                    controller.write(text.getText());
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        new SimpleGUI().display();
    }
}
