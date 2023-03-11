package view;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MenuBar extends JPanel {
    /**
     * Creates the file menu.
     * @param theFrame the object onto which all the menu items are loaded onto.
     * @return returns the fileMenu object that is created in this method.
     */

    static boolean myIsPaused;
    public static JMenuBar createFileMenu(final JFrame theFrame, final Timer theTimer) {
        final JMenuBar fileMenu = new JMenuBar();
        final JMenu gameMenu = new JMenu("Game");
        final JMenu aboutMenu = new JMenu("About");
        final JMenu optionsMenu = new JMenu("Options");
        final JMenu newGameMenu = new JMenu("New Game");
        JMenuItem menuItem;
        menuItem = new JMenuItem("This Window");
        //Sets a hotkey of CTRL + N to activate the event
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        //Activates the event upon clicking the button or pressing the hotkey
        menuItem.addActionListener(
                theEvent -> System.out.println("Starting a new game in this window!"));
        newGameMenu.add(menuItem);
        menuItem = new JMenuItem("New Window");
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
        final JMenuItem pauseMenuItem = new JMenuItem("Pause Game");
        pauseMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent theEvent) {
                if (myIsPaused) {
                    theTimer.start();
                    pauseMenuItem.setText("Pause Game");
                    myIsPaused = false;
                } else {
                    theTimer.stop();
                    pauseMenuItem.setText("Unpause Game");
                    myIsPaused = true;
                }
            }
        });
        gameMenu.add(pauseMenuItem);
        menuItem = new JMenuItem("Exit Game");
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(theEvent -> theFrame.dispatchEvent(
                new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING)));
        gameMenu.add(menuItem);
        menuItem = new JMenuItem("Controls");
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(
                theEvent -> new ControlsGUI());
        aboutMenu.add(menuItem);
        menuItem = new JMenuItem("Help");
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(
                theEvent -> System.out.println("Information about the game!"));
        aboutMenu.add(menuItem);
        menuItem = new JMenuItem("Option 1");
        menuItem.addActionListener(
                theEvent -> System.out.println("Option 1 working as intended"));
        optionsMenu.add(menuItem);

        //Adds the menus to the menu bar
        fileMenu.add(gameMenu);
        fileMenu.add(aboutMenu);
        fileMenu.add(optionsMenu);
        theFrame.setVisible(true);

        return fileMenu;
    }
}
