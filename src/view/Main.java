package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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



public class Main extends JPanel {
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int TWO = 2;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    public static void main(final String[] theArgs) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        final int userWidth =  (int) size.getWidth();
        final int userHeight = (int) size.getHeight();
        frame.setSize(userWidth, userHeight);
        frame.setJMenuBar(createFileMenu(frame));

        //panels
        final JPanel tetrisBoard = new JPanel();
        final JPanel nextPiece = new JPanel();
        final JPanel userInfo = new JPanel();
        final JPanel rightRegion = new JPanel();
        tetrisBoard.setBackground(Color.red);
        nextPiece.setBackground(Color.blue);
        userInfo.setBackground(Color.green);
        rightRegion.setLayout(new BorderLayout());

        //make the regions
        frame.add(tetrisBoard, BorderLayout.CENTER);
        frame.add(rightRegion, BorderLayout.EAST);
        frame.add(userInfo, BorderLayout.WEST);
        rightRegion.add(nextPiece, BorderLayout.NORTH);
        tetrisBoard.setPreferredSize(new Dimension(userWidth / TWO, userHeight));
        rightRegion.setPreferredSize(new Dimension(userWidth / FOUR, userHeight));
        nextPiece.setPreferredSize(new Dimension(userWidth / FOUR, userWidth / FOUR));
        userInfo.setPreferredSize(new Dimension(userWidth / FOUR, userHeight));

        frame.setVisible(true);
    }

    /**
     * Creates the file menu.
     * @param theFrame the object onto which all the menu items are loaded onto.
     * @return returns the fileMenu object that is created in this method.
     */
    public static JMenuBar createFileMenu(final JFrame theFrame) {
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
        theFrame.setVisible(true);

        return fileMenu;
    }

    /**
     * This method is invoked when the user activates
     * the controls option in the menu bar. It creates
     * a second GUI to hold all the control information.
     */
    private static void loadControlsGui() {
        SwingUtilities.invokeLater(() -> {
            final Main controlsPanel = new Main();
            final JFrame frame = new JFrame("Controls Menu");
            final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            final int userWidth =  (int) size.getWidth() / 2;
            final int userHeight = (int) size.getHeight() / 2;
            System.out.print(" size " + size);
            System.out.print(" userWidth " + userWidth);
            System.out.print(" userHeight " + userHeight);
            frame.setMinimumSize(new Dimension(userWidth, userHeight));
            frame.add(controlsPanel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }


}