package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

/**
 * A very simple program using a graphical interface.
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private static final String TITLE = "EX 93";
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller;
    private final JTextField text;
    private final JTextArea textArea;
    private final JButton print;
    private final JButton showHistory;

    /**
     * Builds a new SimpleGUI.
     */
    public SimpleGUI() {
        this.controller = new SimpleController();
        final JPanel canvas = new JPanel(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Upper part: Input (North)
        this.text = new JTextField("insert the string");
        canvas.add(text, BorderLayout.NORTH);

        // 2. Center part: History Output (Center) - Added as required
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        canvas.add(textArea, BorderLayout.CENTER);

        // 3. Lower part: Buttons
        this.print = new JButton("Print");
        this.showHistory = new JButton("Show history");

        // Keeping your style: a panel with BorderLayout for buttons
        final JPanel buttonsJPanel = new JPanel(new BorderLayout());
        buttonsJPanel.add(showHistory, BorderLayout.EAST);
        buttonsJPanel.add(print, BorderLayout.WEST);
        canvas.add(buttonsJPanel, BorderLayout.PAGE_END);

        frame.setContentPane(canvas);

        /*
         * Handlers
         */
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final List<String> history = controller.getPrintedStringsHistory();
                final StringBuilder sb = new StringBuilder();
                for (final String s : history) {
                    sb.append(s).append('\n'); //doing this to not have robelms with PMD
                }
                textArea.setText(sb.toString());
            }
        });

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.setNextStringToPrint(text.getText());
                    controller.printCurrentString();
                } catch (final IllegalStateException exception) {
                    exception.printStackTrace(); //NOPMD
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
