package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import it.unibo.oop.lab.mvcio.Controller;
/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();
    private final Controller controller = new Controller();

    /**
     * builds a new {@link SimpleGUI}.
     */
    private SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel(new BorderLayout());
        // North panel with pathname and button to browse
        final JPanel northPanel = new JPanel(new BorderLayout());
        final JTextField pathName = new JTextField(
                controller.getFilePath());
        final JButton browseBtn = new JButton("Browse");
        pathName.setEditable(false);
        northPanel.add(pathName, BorderLayout.CENTER);
        northPanel.add(browseBtn, BorderLayout.EAST);
        canvas.add(northPanel, BorderLayout.NORTH);
        // Handler for browse button
        browseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fc = new JFileChooser("Choose");
                //fc.setSelectedFile(controller.getFile());
                final int res = fc.showSaveDialog(frame);
                switch (res) {
                case JFileChooser.APPROVE_OPTION :
                    final File newFile = fc.getSelectedFile();
                    controller.setFile(newFile);
                    pathName.setText(newFile.getPath());
                    break;
                case JFileChooser.CANCEL_OPTION :
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "l-ffdfjh");
                    break;
                }
            }
        });
        // TextArea and button for save the text inside a file
        final JTextArea textArea = new JTextArea();
        textArea.setBorder(new TitledBorder("Write here..."));
        textArea.setLineWrap(true);
        final JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        canvas.add(scroll, BorderLayout.CENTER);
        final JButton saveBtn = new JButton("Save");
        canvas.add(saveBtn, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Handler for save button
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String text = textArea.getText();
                try {
                    controller.saveString(text);
                } catch (IOException ex) {
                    System.err.println("IOException: " + ex);
                }
            }
        });
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }

}
