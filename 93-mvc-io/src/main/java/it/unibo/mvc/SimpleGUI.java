package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {
    private static SimpleController controller = new SimpleController();
    private static final int PROPORTION = 5;
    private static final String TITLE = "EX 92";
    private final JFrame frame = new JFrame(TITLE);
    private final JTextField text;
    private final JButton print;
    private final JButton showHistory;

    SimpleGUI() {
        final JPanel canvas = new JPanel(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.text = new JTextField("insert the string");
        canvas.add(text, BorderLayout.CENTER);
        this.print = new JButton("print");
        this.showHistory = new JButton("show history");
        //I MUST ADD A NEW FRAME WHERE I CAN PUT THE TWO BUTONS AND THEN ADD THEM TO THE END
        final JPanel buttonsJPanel = new JPanel(new BorderLayout());
        buttonsJPanel.add(showHistory, BorderLayout.EAST);
        buttonsJPanel.add(print, BorderLayout.WEST);
        canvas.add(buttonsJPanel, BorderLayout.PAGE_END);
        frame.setContentPane(canvas);
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.out.println("the history is:"); //NOPMD
                System.out.println(controller.getHistory()); //NOPMD
            }
        });
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.setNextString(text.getText());
                    controller.print();
                } catch (final IllegalAccessException exception) {
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
