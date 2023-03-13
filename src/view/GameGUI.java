/*
 * TCSS 305 - Winter 2023
 * Final Group Project - Tetris
 */

package view;

import static view.ButtonsPanel.myGameStart;
import static view.MenuBar.createFileMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Board;
import model.ModelTimer;

/**
 * This program defines the behavior and methods contained in objects of the GameGUI class.
 *
 * @author Evan Abrahamson
 * @author Aryan Damle
 * @author Martha Emerson
 * @author Keegan Sanders
 * @version Winter 2023
 */
public class GameGUI implements PropertyChangeListener {
    /**
     * A constant for dividing the total height or width of the Board.
     */
    private static final int FOUR = 4;
    /**
     * The base timer tick.
     */
    private static final int BASE_TIMER_TICK = 600;
    /**
     * The height of the user's screen.
     */
    private int myUserHeight;
    /**
     * The width of the user's screen.
     */
    private int myUserWidth;
    /**
     * The main GUI frame.
     */
    private JFrame myFrame;
    /**
     * The panel that displays user info.
     */
    private UserInfo myUserInfo;
    /**
     * The Board panel.
     */
    private Board myBoard;
    /**
     * Timer instance variable.
     */
    private Timer myTimer;
    /**
     * The rate at which the Timer ticks.
     */
    private int myTimerTick;

    /**
     * A constructor for class GameGUI.
     */
    public GameGUI() {
        init();
    }

    /**
     * Creates the initial conditions of the Tetris game.
     */
    private void init() {
        myBoard = new Board();
        myBoard.newGame();
        myTimerTick = BASE_TIMER_TICK;
        myTimer = new Timer(myTimerTick, new ModelTimer(myBoard));
        myTimer.addActionListener(theEvent -> myTimer.setDelay(myUserInfo.getTimerTick()));
        myFrame = new JFrame();

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        myUserWidth = (int) size.getWidth();
        myUserHeight = (int) size.getHeight();
        myUserInfo = new UserInfo(myUserWidth, myUserHeight, myTimerTick);
        myBoard.addPropertyChangeListener(myUserInfo);

        myFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent thePressedKey) {
                if (myTimer.isRunning()) {
                    //arrow key events
                    control(thePressedKey.getKeyCode());

                    switch (thePressedKey.getKeyChar()) {
                        case 'a', 'A' -> myBoard.left();
                        case 'd', 'D' -> myBoard.right();
                        case 'w', 'W' -> myBoard.rotateCW();
                        case 's', 'S' -> myBoard.down();
                        default -> {
                        }
                    }
                }
            }
        });
        myFrame.setSize(myUserWidth, myUserHeight);
        myFrame.setJMenuBar(createFileMenu(myFrame, myTimer));
    }

    /**
     * Starts the Tetris game.
     */
    public void start() {
        setup();
        final TetrisBoard tetrisBoard = new TetrisBoard(myUserWidth, myUserHeight);
        tetrisBoard.play("src/music/Pacman_Introduction_Music-KP-826387403.wav");
        myFrame.setVisible(true);
        if (!myGameStart) {
            myTimer.start();
        }
    }

    /**
     * Sets up the Tetris game.
     */
    public void setup() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(myUserWidth, myUserHeight);
        myFrame.setJMenuBar(createFileMenu(myFrame, myTimer));

        //panels
        final TetrisBoard tb = new TetrisBoard(myUserWidth, myUserHeight);
        final NextPiece np = new NextPiece(myUserWidth);
        final ButtonsPanel bp = new ButtonsPanel();
        final JPanel userInfo = new JPanel();
        final UserInfo ui = new UserInfo(myUserWidth, myUserHeight, myTimerTick);
        final JPanel rightRegion = new JPanel();

        userInfo.setBackground(Color.LIGHT_GRAY);
        rightRegion.setLayout(new BorderLayout());

        //make the regions
        myFrame.add(tb.getTetrisBoard(), BorderLayout.CENTER);
        myFrame.add(rightRegion, BorderLayout.EAST);
        myFrame.add(userInfo, BorderLayout.WEST);
        rightRegion.add(bp.getMyButtonsPanel(), BorderLayout.SOUTH);
        myFrame.add(ui.getUserInfo(), BorderLayout.WEST);
        rightRegion.add(np.getNextPiece(), BorderLayout.NORTH);
        rightRegion.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserHeight));

        myBoard.addPropertyChangeListener(np);
        myBoard.addPropertyChangeListener(tb);
        myBoard.addPropertyChangeListener(ui);

        myFrame.setVisible(true);
    }

    private void control(final int theKeyCode) {
        switch (theKeyCode) {
            case KeyEvent.VK_LEFT -> myBoard.left();
            case KeyEvent.VK_RIGHT -> myBoard.right();
            case KeyEvent.VK_UP -> myBoard.rotateCW();
            case KeyEvent.VK_DOWN -> myBoard.down();
            case KeyEvent.VK_SPACE -> myBoard.drop();
            default -> {
            }
        }


    }



    /**
     *
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {

    }
}

