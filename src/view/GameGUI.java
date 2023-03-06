package view;

import model.Board;
import model.ModelTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import static view.MenuBar.createFileMenu;

public class GameGUI implements Observer {
    /**
     * Frame is user screen height
     */
    private int myUserHeight = 0;
    /**
     * Frame is user screen width
     */
    private int myUserWidth = 0;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int TWO = 2;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
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
    private Board myBoard;
    private Timer myTimer;

    /**
     * Constructor
     */
    public GameGUI() {
        init();
    }

    private void init() {
        myTimer = new Timer(60, new ModelTimer(myBoard));
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
        TetrisBoard tb = new TetrisBoard(myUserWidth, myUserHeight);
        NextPiece np = new NextPiece(myUserWidth);
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


    public void keyPressed(final KeyEvent pressedKey) {
        //arrow key events
        switch (pressedKey.getKeyCode()) {
            case KeyEvent.VK_LEFT -> myBoard.left();
            case KeyEvent.VK_RIGHT -> myBoard.right();
            case KeyEvent.VK_ENTER -> start();
        }

        switch (pressedKey.getKeyChar()) {
            case 'a' -> myBoard.left();
            case 'A' -> myBoard.left();
            case 'd' -> myBoard.right();
            case 'D' -> myBoard.right();
        }
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}

