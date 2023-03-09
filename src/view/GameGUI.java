package view;

import static view.MenuBar.createFileMenu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import model.Board;
import model.ModelTimer;


public class GameGUI implements Observer {
    /**
     * Avoid checkstyle 'magic number' error.
     */
    private static final int FOUR = 4;
    /**
     * Avoid checkstyle 'magic number' error.
     */
    private static final int TIMER_TICK = 600;
    /**
     * Frame is user screen height.
     */
    private int myUserHeight;
    /**
     * Frame is user screen width.
     */
    private int myUserWidth;
    /**
     * GUI frame.
     */
    private JFrame myFrame;
    /**
     * User info panel.
     */
    private JPanel myUserInfo;
    /**
     * Right side region of Frame.
     */
    private JPanel myRightRegion;
    /**
     * Board instance variable.
     */
    private Board myBoard;
    /**
     * TImer instance variable.
     */
    private Timer myTimer;

    /**
     * Constructor.
     */
    public GameGUI() {
        init();
    }

    private void init() {
        myBoard = new Board();
        myBoard.newGame();
        myTimer = new Timer(TIMER_TICK, new ModelTimer(myBoard));
        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        myUserWidth = (int) size.getWidth();
        myUserHeight = (int) size.getHeight();
        myFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(final KeyEvent thePressedKey) {
                //arrow key events
                switch (thePressedKey.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> myBoard.left();
                    case KeyEvent.VK_RIGHT -> myBoard.right();
                    case KeyEvent.VK_UP -> myBoard.rotateCW();
                    case KeyEvent.VK_DOWN -> myBoard.down();
                    case KeyEvent.VK_ENTER -> start();
                    case KeyEvent.VK_SPACE -> myBoard.drop();
                    default -> {
                        break;
                    }
                }

                switch (thePressedKey.getKeyChar()) {
                    case 'a', 'A' -> myBoard.left();
                    case 'd', 'D' -> myBoard.right();
                    case 'w', 'W' -> myBoard.rotateCW();
                    case 's', 'S' -> myBoard.down();
                    default -> {
                        break;
                    }
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        myFrame.setSize(myUserWidth, myUserHeight);
        myFrame.setJMenuBar(createFileMenu(myFrame));

        //panels

    }

    public void start() {
        setup();
        myTimer.start();
        myFrame.setVisible(true);
    }

    public void setup() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        myFrame.setSize(myUserWidth, myUserHeight);
        myFrame.setJMenuBar(createFileMenu(myFrame));

        //panels
        final TetrisBoard tb = new TetrisBoard(myUserWidth, myUserHeight);
        final NextPiece np = new NextPiece(myUserWidth);
        final JPanel userInfo = new JPanel();
        final JPanel rightRegion = new JPanel();
        userInfo.setBackground(Color.green);
        rightRegion.setLayout(new BorderLayout());

        //make the regions
        myFrame.add(tb.getTetrisBoard(), BorderLayout.CENTER);
        myFrame.add(rightRegion, BorderLayout.EAST);
        myFrame.add(userInfo, BorderLayout.WEST);
        rightRegion.add(np.getNextPiece(), BorderLayout.NORTH);
        rightRegion.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserHeight));
        userInfo.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserHeight));

        myBoard.addPropertyChangeListener(np);
        myBoard.addPropertyChangeListener(tb);

        myFrame.setVisible(true);
    }

    @Override
    public void update(final Observable theObservable, final Object theArg) {

    }
}

