/*
 * TCSS 305 - Winter 2023
 * Final Group Project - Tetris
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
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
     * The Menu label for the Game option.
     */
    private static final String GAME = "Game";
    /**
     * The Menu label for the About menu.
     */
    private static final String ABOUT = "About";
    /**
     * The Menu label for the Options menu.
     */
    private static final String OPTIONS = "Options";
    /**
     * The Menu label for the New Game menu.
     */
    private static final String NEW_GAME = "New Game";
    /**
     * The Menu label for the new game in This Window option.
     */
    private static final String THIS_WINDOW = "This Window";
    /**
     * The Menu label for the new game in a New Window option.
     */
    private static final String NEW_WINDOW = "New Window";
    /**
     * The Menu label for the Pause Game option.
     */
    private static final String PAUSE_GAME = "Pause Game";
    /**
     * The Menu label for the Unpause Game option.
     */
    private static final String UNPAUSE_GAME = "Unpause Game";
    /**
     * The text for Starting a new game in this Window! notification.
     */
    private static final String STARTING_NEW_GAME = "Starting a new game in this window!";
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
     * The Menu label for the game info menu.
     */
    private static final String GAME_INFO = "Information about the game!";
    /**
     * The Menu label for Option 1.
     */
    private static final String OPTION_1 = "Option 1";
    /**
     * The text for Option 1 working as intended notification.
     */
    private static final String OPTION_1_WORKING = "Option 1 working as intended";
    /**
     * Whether the game is paused.
     */
    private static boolean myIsPaused;

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
        final JMenu optionsMenu = new JMenu(OPTIONS);
        final JMenu newGameMenu = new JMenu(NEW_GAME);
        JMenuItem menuItem;
        menuItem = new JMenuItem(THIS_WINDOW);

        // Sets a hotkey of CTRL + N to activate the event
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        // Activates the event upon clicking the button or pressing the hotkey
        menuItem.addActionListener(
                theEvent -> System.out.println(STARTING_NEW_GAME));
        newGameMenu.add(menuItem);
        menuItem = new JMenuItem(NEW_WINDOW);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N,
                        InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));

        // Activates the event upon clicking the button or pressing the hotkey
        menuItem.addActionListener(
                theEvent -> new GameGUI().start());
        newGameMenu.add(menuItem);
        gameMenu.add(newGameMenu);

        // adds the Pause option to the menu
        myIsPaused = false;
        final JMenuItem pauseMenuItem = new JMenuItem(PAUSE_GAME);
        pauseMenuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        pauseMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                if (myIsPaused) {
                    theTimer.start();
                    pauseMenuItem.setText(PAUSE_GAME);
                    myIsPaused = false;
                } else {
                    theTimer.stop();
                    pauseMenuItem.setText(UNPAUSE_GAME);
                    myIsPaused = true;
                }
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
        menuItem.addActionListener(
                theEvent -> System.out.println(GAME_INFO));
        aboutMenu.add(menuItem);

        // adds Option 1 to the menu
        menuItem = new JMenuItem(OPTION_1);
        menuItem.addActionListener(
                theEvent -> System.out.println(OPTION_1_WORKING));
        optionsMenu.add(menuItem);

        //Adds the menus to the menu bar
        fileMenu.add(gameMenu);
        fileMenu.add(aboutMenu);
        fileMenu.add(optionsMenu);
        theFrame.setVisible(true);

        return fileMenu;
    }
}
