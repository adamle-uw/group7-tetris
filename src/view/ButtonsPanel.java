/*
 * TCSS 305 - Winter 2023
 * Final Group Project - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 * This program defines the methods and behavior for Objects of the ButtonsPanel class.
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
     * Whether the game has started.
     */
    protected static boolean myGameStart;
    /**
     * A constant for dividing the total height or width of the Board.
     */
    private static final int FOUR = 4;
    /**
     * The Panel of Buttons.
     */
    private final JPanel myButtonsPanel;

    /**
     * A constructor for the class ButtonsPanel.
     */
    public ButtonsPanel() {
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        final int userWidth = (int) size.getWidth();
        myButtonsPanel = new JPanel();
        myButtonsPanel.setLayout(new FlowLayout());
        this.myButtonsPanel.setBackground(Color.WHITE);
        this.myButtonsPanel.setPreferredSize(new Dimension(userWidth / FOUR,
                userWidth / FOUR));
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