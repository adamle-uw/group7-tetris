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
 * This program ...
 *
 * @author Evan Abrahamson
 * @author Aryan Damle
 * @author Martha Emerson
 * @author Keegan Sanders
 * @version Winter 2023
 */
public class MenuBar extends JPanel {
    /**
     * ...
     */
    private static final String GAME = "Game";
    /**
     * ...
     */
    private static final String ABOUT = "About";
    /**
     * ...
     */
    private static final String OPTIONS = "Options";
    /**
     * ...
     */
    private static final String NEW_GAME = "New Game";
    /**
     * ...
     */
    private static final String THIS_WINDOW = "This Window";
    /**
     * ...
     */
    private static final String NEW_WINDOW = "New Window";
    /**
     * ...
     */
    private static final String PAUSE_GAME = "Pause Game";
    /**
     * ...
     */
    private static final String UNPAUSE_GAME = "Unpause Game";
    /**
     * ...
     */
    private static final String STARTING_NEW_GAME = "Starting a new game in this window!";
    /**
     * ...
     */
    private static final String HELP = "Help";
    /**
     * ...
     */
    private static final String CONTROLS = "Controls";
    /**
     * ...
     */
    private static final String EXIT_GAME = "Exit Game";
    /**
     * ...
     */
    private static final String GAME_INFO = "Information about the game!";
    /**
     * ...
     */
    private static final String OPTION_1 = "Option 1";
    /**
     * ...
     */
    private static final String OPTION_1_WORKING = "Option 1 working as intended";
    /**
     * ...
     */
    private static boolean myIsPaused;

    /**
     * Creates the file menu.
     *
     * @param theFrame the object onto which all the menu items are loaded onto.
     * @param theTimer ...
     * @return returns the fileMenu object that is created in this method.
     */
    public static JMenuBar createFileMenu(final JFrame theFrame, final Timer theTimer) {
        final JMenuBar fileMenu = new JMenuBar();
        final JMenu gameMenu = new JMenu(GAME);
        final JMenu aboutMenu = new JMenu(ABOUT);
        final JMenu optionsMenu = new JMenu(OPTIONS);
        final JMenu newGameMenu = new JMenu(NEW_GAME);
        JMenuItem menuItem;
        menuItem = new JMenuItem(THIS_WINDOW);
        //Sets a hotkey of CTRL + N to activate the event
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        //Activates the event upon clicking the button or pressing the hotkey
        menuItem.addActionListener(
                theEvent -> System.out.println(STARTING_NEW_GAME));
        newGameMenu.add(menuItem);
        menuItem = new JMenuItem(NEW_WINDOW);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N,
                        InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        //Activates the event upon clicking the button or pressing the hotkey
        menuItem.addActionListener(
                theEvent -> new GameGUI().start());
        newGameMenu.add(menuItem);
        gameMenu.add(newGameMenu);
        //Adds item to the menu
        //gameMenu.add(menuItem);
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
        menuItem = new JMenuItem(EXIT_GAME);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(theEvent -> theFrame.dispatchEvent(
                new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING)));
        gameMenu.add(menuItem);
        menuItem = new JMenuItem(CONTROLS);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(
                theEvent -> new ControlsGUI());
        aboutMenu.add(menuItem);
        menuItem = new JMenuItem(HELP);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(
                theEvent -> System.out.println(GAME_INFO));
        aboutMenu.add(menuItem);
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
