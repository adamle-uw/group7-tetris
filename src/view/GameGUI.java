package view;

import model.Board;

import javax.swing.*;
import java.awt.*;

import static view.MenuBar.createFileMenu;

public class GameGUI {
    /**Frame is user screen height*/
    private int myUserHeight = 0;
    /**Frame is user screen width*/
    private int myUserWidth = 0;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int TWO = 2;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    /**GUI frame.*/
    private JFrame myFrame;
    /**make tetris board.*/
    private JPanel myTetrisBoard;
    /**Next piece panel.*/
    private JPanel myNextPiece;
    /**User info panel.*/
    private JPanel myUserInfo;
    /**Right side region of Frame.*/
    private JPanel myRightRegion;
    private Board myBoard;
    /**Constructor*/
    public GameGUI() {
        init();
    }

    private void init() {
        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        myUserWidth =  (int) size.getWidth();
        myUserHeight = (int) size.getHeight();
        myFrame.setSize(myUserWidth, myUserHeight);
        myFrame.setJMenuBar(createFileMenu(myFrame));

        //panels

    }

    public void start() {
        setup();

        myFrame.setVisible(true);
    }

    public void setup() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        myFrame.setSize(myUserWidth, myUserHeight);
        myFrame.setJMenuBar(createFileMenu(myFrame));

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
        myFrame.add(tetrisBoard, BorderLayout.CENTER);
        myFrame.add(rightRegion, BorderLayout.EAST);
        myFrame.add(userInfo, BorderLayout.WEST);
        rightRegion.add(nextPiece, BorderLayout.NORTH);
        tetrisBoard.setPreferredSize(new Dimension(myUserWidth / TWO, myUserHeight));
        rightRegion.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserHeight));
        nextPiece.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserHeight / FOUR));
        userInfo.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserHeight));

        myFrame.setVisible(true);
    }
}
