/*
 * TCSS 305 - Winter 2023
 * Final Group Project - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;

/**
 * This program defines the methods and behavior for Objects of the ButtonsPanel class.*
 * The ButtonsPanel contains Buttons that allow the Tetris game-player to start, pause,
 * unpause, and end the game.
 *
 * @author Evan Abrahamson
 * @author Aryan Damle
 * @author Martha Emerson
 * @author Keegan Sanders
 * @version Winter 2023
 */
public class ButtonsPanel extends JPanel {
    /**
     * A constant for dividing the total height or width of the Board.
     */
    private static final int FOUR = 4;
    /**
     * The Button label for Start.
     */
    private static final String START = "Start";
    /**
     * The Button label for Pause.
     */
    private static final String PAUSE = "Pause";
    /**
     * The Button label for Unpause.
     */
    private static final String UNPAUSE = "Unpause";
    /**
     * The Button label for End.
     */
    private static final String END = "End";
    /**
     * Whether the game is currently paused.
     */
    private static boolean myIsPaused;
    /**
     * The Panel of Buttons.
     */
    private JPanel myButtonsPanel;
    /**
     * The Pause Button.
     */
    private JButton myPause = new JButton();
    /**
     * Whether the game has started.
     */
    public static boolean myGameStart;

    /**
     * A parameterless constructor for the class ButtonsPanel.
     */
    public ButtonsPanel() {
    }

    /**
     * A constructor for the class ButtonsPanel.
     *
     * @param theTimer the game timer.
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
     * Returns the Panel of Buttons.
     *
     * @return the Panel of Buttons.
     */
    public JPanel getMyButtonsPanel() {
        return myButtonsPanel;
    }
}
