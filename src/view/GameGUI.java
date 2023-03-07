package view;

import static view.MenuBar.createFileMenu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import model.Board;
import model.ModelTimer;


public class GameGUI implements Observer {
    /**
     * Avoid checkstyle 'magic number' error.
     */
    private static final int TWO = 2;
    /**
     * Avoid checkstyle 'magic number' error.
     */
    private static final int FOUR = 4;
    /**
     * Avoid checkstyle 'magic number' error.
     */
    private static final int SIXTY = 60;
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
        myTimer = new Timer(SIXTY, new ModelTimer(myBoard));
        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        myUserWidth = (int) size.getWidth();
        myUserHeight = (int) size.getHeight();
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
        final JPanel tetrisBoard = new JPanel();
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

        //add property change listeners to the board
        final Board b = new Board();
        b.addPropertyChangeListener(np);
        b.addPropertyChangeListener(tb);

        //b.prepareNextMovablePiece(); //Set method to public in Board.java, tests NextPiecePCL
        //b.step();  //Comment out down() on step() in Board.java, tests TetrisBoardPCL

        myFrame.setVisible(true);
    }


    public void keyPressed(final KeyEvent thePressedKey) {
        //arrow key events
        switch (thePressedKey.getKeyCode()) {
            case KeyEvent.VK_LEFT -> myBoard.left();
            case KeyEvent.VK_RIGHT -> myBoard.right();
            case KeyEvent.VK_ENTER -> start();
            default -> {
                break;
            }
        }

        switch (thePressedKey.getKeyChar()) {
            case 'a', 'A' -> myBoard.left();
            case 'd', 'D' -> myBoard.right();
            default -> {
                break;
            }
        }
    }


    @Override
    public void update(final Observable theObservable, final Object theArg) {

    }
}

