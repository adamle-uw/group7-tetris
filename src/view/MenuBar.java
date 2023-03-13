/*
 * TCSS 305 - Winter 2023
 * Final Group Project - Tetris
 */

package view;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * This program defines the methods and behavior for Objects of the MenuBar class.
 *
 * @author Evan Abrahamson
 * @author Aryan Damle
 * @author Martha Emerson
 * @author Keegan Sanders
 * @version Winter 2023
 */
public class MenuBar extends JPanel {
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int TWO = 2;
    /**
     * The Menu label for the Game option.
     */
    private static final String GAME = "Game";
    /**
     * The Menu label for the About menu.
     */
    private static final String ABOUT = "About";
    /**
     * The Menu label for the New Game menu.
     */
    private static final String NEW_GAME = "New Game";
    /**
     * The Menu label for the Pause Game option.
     */
    private static final String PAUSE_GAME = "Pause Game";
    /**
     * The Menu label for the Unpause Game option.
     */
    private static final String UNPAUSE_GAME = "Unpause Game";
    /**
     * The Menu label for the Help menu.
     */
    private static final String HELP = "Help";
    /**
     * The Menu label for the Controls menu.
     */
    private static final String CONTROLS = "Controls";
    /**
     * The Menu label for the Exit Game option.
     */
    private static final String EXIT_GAME = "Exit Game";
    /**
     * Whether the game is paused.
     */
    private static boolean myIsPaused;
    /**
     * The font size.
     */
    private static final int FONT_SIZE = 30;

    /**
     * Returns the fileMenu object that is created in this method.
     * Creates the file menu.
     *
     * @param theFrame  the Object onto which all the menu items are loaded.
     * @param theTimer  the game Timer.
     * @return          the fileMenu object that is created in this method.
     */
    public static JMenuBar createFileMenu(final JFrame theFrame, final Timer theTimer) {
        final JMenuBar fileMenu = new JMenuBar();
        final JMenu gameMenu = new JMenu(GAME);
        final JMenu aboutMenu = new JMenu(ABOUT);
        final JMenu newGameMenu = new JMenu(NEW_GAME);
        JMenuItem menuItem;
        menuItem = new JMenuItem(NEW_GAME);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N,
                        InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));

        // Activates the event upon clicking the button or pressing the hotkey
        menuItem.addActionListener(
                theEvent -> theFrame.dispose());
        menuItem.addActionListener(
                theEvent -> new GameGUI().start());
        gameMenu.add(menuItem);

        // adds the Pause option to the menu
        myIsPaused = false;
        final JMenuItem pauseMenuItem = new JMenuItem(PAUSE_GAME);
        pauseMenuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        pauseMenuItem.addActionListener(theEvent -> {
            if (myIsPaused) {
                theTimer.start();
                pauseMenuItem.setText(PAUSE_GAME);
                myIsPaused = false;
            } else {
                theTimer.stop();
                pauseMenuItem.setText(UNPAUSE_GAME);
                myIsPaused = true;
            }
        });
        gameMenu.add(pauseMenuItem);

        // adds Exit Game option to the menu
        menuItem = new JMenuItem(EXIT_GAME);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(theEvent -> theFrame.dispatchEvent(
                new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING)));
        gameMenu.add(menuItem);

        // adds Controls Menu option to the menu
        menuItem = new JMenuItem(CONTROLS);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(
                theEvent -> new ControlsGUI());
        aboutMenu.add(menuItem);

        // adds Help option to the menu
        menuItem = new JMenuItem(HELP);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(e -> {
            final JFrame helpWindow = new JFrame(HELP);
            helpWindow.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()
                            / TWO,
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / TWO);
            helpWindow.setLocationRelativeTo(null);
            final JLabel helpLabel = new JLabel("<html>How To Play:<br/> <br/> "
                    + "Line up all the blocks to clear a line. Get bonus points for "
                    + "clearing multiple lines! Gain 4 points for each block placed.<br/><br/>"
                    + "1 Line: 40 Points per Level<br/>2 Lines: 100 Points per Level<br/>"
                    + "3 Lines: 300 Points per Level<br/>4 Lines: 1200 Points per Level"
                    + "<br/><br/>Credits:<br/>"
                    + "Pac-Man Intro Music from Spykee75 on"
                    + "YouTube (https://youtu.be/rnf0q1zOTXs)"
                    , JLabel.CENTER);
            helpLabel.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
            helpWindow.add(helpLabel);
            helpWindow.setVisible(true);
        });
        aboutMenu.add(menuItem);


        //Adds the menus to the menu bar
        fileMenu.add(gameMenu);
        fileMenu.add(aboutMenu);
        theFrame.setVisible(true);

        return fileMenu;
    }
}
