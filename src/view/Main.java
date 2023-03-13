/*
 * TCSS 305 - Winter 2023
 * Final Group Project - Tetris
 */

package view;

import javax.swing.JPanel;

/**
 * This program runs the Tetris game.
 *
 * @author Evan Abrahamson
 * @author Aryan Damle
 * @author Martha Emerson
 * @author Keegan Sanders
 * @version Winter 2023
 */

public class Main extends JPanel {
    /**
     * Constructs the main GUI window frame.
     *
     * @param theArgs Command line arguments (ignored).
     */
    public static void main(final String[] theArgs) {
        new GameGUI().start();
    }
}
