package view;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static view.MenuBar.createFileMenu;

public class GameGUI implements PropertyChangeListener {
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

    private static final String NEW_PIECE_PROPERTY = "NewPieceCreate";
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

    private Color myNextPieceColor;

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
        myNextPiece = new JPanel();
        final JPanel userInfo = new JPanel();
        final JPanel rightRegion = new JPanel();
        tetrisBoard.setBackground(Color.red);
        myNextPiece.setBackground(Color.blue);
        myNextPiece.addPropertyChangeListener(this);
        myNextPieceColor = Color.red;
        userInfo.setBackground(Color.green);
        rightRegion.setLayout(new BorderLayout());

        //make the regions
        myFrame.add(tetrisBoard, BorderLayout.CENTER);
        myFrame.add(rightRegion, BorderLayout.EAST);
        myFrame.add(userInfo, BorderLayout.WEST);
        rightRegion.add(myNextPiece, BorderLayout.NORTH);
        tetrisBoard.setPreferredSize(new Dimension(myUserWidth / TWO, myUserHeight));
        rightRegion.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserHeight));
        myNextPiece.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserHeight / FOUR));
        userInfo.setPreferredSize(new Dimension(myUserWidth / FOUR, myUserHeight));

        myFrame.setVisible(true);
    }

    public void propertyChange(PropertyChangeEvent theEvent) {
        if (NEW_PIECE_PROPERTY.equals(theEvent.getPropertyName())) {
            myNextPiece.setBackground(Color.red);
        }
    }
}
