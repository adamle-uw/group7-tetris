package view;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class TempGUI extends JPanel {
    /**The window title.*/
    private static final String TITLE = "Tetris";
    /**The size of the window.*/
    private static final int WINDOW_SIZE = 200;
    /**Constructor just to create a temp. window.*/
    public TempGUI() {
        super();

        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createEmptyBorder(
                WINDOW_SIZE, WINDOW_SIZE * 2, WINDOW_SIZE, WINDOW_SIZE * 2));
    }

    /**
     * Creates a file menu for the program.
     * @return Returns a Menu Bar
     */
    public JMenuBar createFileMenu(final JFrame theFrame) {
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
        menuItem.addActionListener(theEvent -> loadMainGui());
        menuItem.addActionListener(
                theEvent -> System.out.println("Starting a new game in a new window!"));
        newGameMenu.add(menuItem);
        gameMenu.add(newGameMenu);
        //Adds item to the menu
        //gameMenu.add(menuItem);
        menuItem = new JMenuItem("Pause Game");
        menuItem.addActionListener(theEvent -> System.out.println("Pausing game!"));
        gameMenu.add(menuItem);
        menuItem = new JMenuItem("Exit Game");
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(theEvent -> theFrame.dispatchEvent(
                        new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING)));
        gameMenu.add(menuItem);
        menuItem = new JMenuItem("Controls");
        menuItem.addActionListener(theEvent -> System.out.println("List controls here"));
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(
                theEvent -> loadControlsGui());
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

        return fileMenu;
    }

    private static void loadControlsGui() {
        SwingUtilities.invokeLater(() -> {
            final TempGUI controlsPanel = new TempGUI();
            final JFrame window = new JFrame(TITLE);
            window.setSize(WINDOW_SIZE, WINDOW_SIZE);
            window.add(controlsPanel);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.pack();
            window.setVisible(true);
        });
    }
    private static void loadMainGui() {
        SwingUtilities.invokeLater(() -> {
            final TempGUI mainPanel = new TempGUI();
            final JFrame window = new JFrame(TITLE);
            window.setSize(WINDOW_SIZE, WINDOW_SIZE);
            window.add(mainPanel);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.pack();
            window.setVisible(true);
            window.setJMenuBar(mainPanel.createFileMenu(window));
        });
    }
    /**Main method used to create the window and file menu.*/
    public static void main(final String[] theArgs) {
        loadMainGui();
    }
}
