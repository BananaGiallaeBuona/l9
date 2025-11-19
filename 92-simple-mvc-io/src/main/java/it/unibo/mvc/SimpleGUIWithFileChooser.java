package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static Controller controller = new Controller();
    private static final int PROPORTION = 5;
    private static final String TITLE = "EX 92.3";
    private final JFrame frame = new JFrame(TITLE);
    private final JTextField textField;
    private final JButton browseButton;

    SimpleGUIWithFileChooser() {
        this.textField = new JTextField(controller.getFileName());
        this.textField.setEditable(false);
        this.browseButton = new JButton("browse");
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel internalPanel = new JPanel(new BorderLayout());
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.add(internalPanel, BorderLayout.NORTH);
        internalPanel.add(textField, BorderLayout.CENTER);
        internalPanel.add(browseButton, BorderLayout.LINE_END);
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                try { 
                    final JFileChooser chooser = new JFileChooser();
                    final int result = chooser.showSaveDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION){
                        controller.setFile(chooser.getSelectedFile());
                        textField.setText(controller.getFileName());
                    } else if (result == JFileChooser.CANCEL_OPTION) {}
                } catch (final Exception e) {
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
        new SimpleGUIWithFileChooser().display();
    }
}
