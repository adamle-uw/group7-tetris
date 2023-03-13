package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This program ...
 *
 * @author Evan Abrahamson
 * @author Aryan Damle
 * @author Martha Emerson
 * @author Keegan Sanders
 * @version Winter 2023
 */
public class ButtonsPanel extends JPanel {
    /**
     * ...
     */
    private static final int FOUR = 4;
    /**
     * ...
     */
    private static final String START = "Start";
    /**
     * ...
     */
    private static final String PAUSE = "Pause";
    /**
     * ...
     */
    private static final String UNPAUSE = "Unpause";
    /**
     * ...
     */
    private static final String END = "End";
    /**
     * ...
     */
    private static boolean myIsPaused;
    /**
     * ...
     */
    private JPanel myButtonsPanel;
    /**
     * ...
     */
    private JButton myPause;
    /**
     * ...
     */
    public static boolean myGameStart;
    /**
     * ...
     *
     * @param theTimer ...
     */
    public ButtonsPanel(final Timer theTimer) {
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        final int userWidth = (int) size.getWidth();
        myButtonsPanel = new JPanel();
        myButtonsPanel.setLayout(new FlowLayout());
        this.myButtonsPanel.setBackground(Color.WHITE);
        this.myButtonsPanel.setPreferredSize(new Dimension(userWidth / FOUR,
                userWidth / FOUR));
        final JButton start = new JButton(START);
        final JButton pause = new JButton(PAUSE);
        final JButton end = new JButton(END);
        myIsPaused = false;
        start.addActionListener(e -> {
            myGameStart = true;
        });
        pause.addActionListener(e -> {
            if (myIsPaused) {
                theTimer.start();
                myPause.setText(PAUSE);
                myIsPaused = false;
            } else {
                theTimer.stop();
                myPause.setText(UNPAUSE);
                myIsPaused = true;
            }
        });
        end.addActionListener(e -> {
            theTimer.stop();

        });


        myButtonsPanel.add(start);
        myButtonsPanel.add(pause);
        myButtonsPanel.add(end);

    }

    /**
     * ...
     */
    public ButtonsPanel() {

    }

    /**
     * ...
     *
     * @return ...
     */
    public JPanel getMyButtonsPanel() {
        return myButtonsPanel;
    }


}
