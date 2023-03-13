/*
 * TCSS 305 - Winter 2023
 * Final Group Project - Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import model.Board;
import model.Point;

/**
 * This program defines the behavior and methods contained in objects of the TetrisBoard class.
 *
 * @author Evan Abrahamson
 * @author Aryan Damle
 * @author Martha Emerson
 * @author Keegan Sanders
 * @version Winter 2023
 */

public class TetrisBoard implements PropertyChangeListener {
    /**
     * A constant for dividing board measurements in half.
     */
    private static final int TWO = 2;
    /**
     * A constant for dividing the total height or width of the Board.
     */
    private static final int FIFTY = 50;
    /**
     * The number of columns on the Tetris game board.
     */
    private static final int COLUMNS = 10;
    /**
     * The number of rows on the Tetris game board.
     */
    private static final int ROWS = 20;
    /**
     * Property value for Tetris Board Change property listener.
     */
    private static final String TETRIS_BOARD_PROPERTY = "TetrisBoardChange";
    /**
     * Creates the Tetris board.
     */
    private final JPanel myTetrisBoard = new JPanel();
    /**
     * The width of the Tetris board.
     */
    private int myWidth;
    /**
     * The height of the Tetris board.
     */
    private int myHeight;
    /**
     * The X Offset of the Cells in the Tetris board.
     */
    private int myXOffset;
    /**
     * The Y Offset of the Cells in the Tetris board.
     */
    private int myYOffset;
    /**
     * The width of the Cells in the Tetris board.
     */
    private int myCellWidth;
    /**
     * The height of the Cells in the Tetris board.
     */
    private int myCellHeight;
    /**Holds whether the block being placed is clearing the board.*/
    private int myIsClearingBoard;
    /**Holds what row the board is being cleared.*/
    private int myBoardClearLevel;

    /**
     * The JPanel that displays the Tetris game board.
     */
    private final TetrisBoardJPanel myTetrisBoardJPanel = new TetrisBoardJPanel();

    /**
     * A constructor for the class ButtonsPanel.
     *
     * @param theUserWidth  the width of the user's computer monitor
     * @param theUserHeight the height of the user's computer monitor
     * @param theBoard      ...
     */
    public TetrisBoard(final int theUserWidth, final int theUserHeight, final Board theBoard) {
        this.myTetrisBoard.setBackground(Color.BLACK);
        this.myTetrisBoard.addPropertyChangeListener(this);

        myTetrisBoard.setPreferredSize(new Dimension(theUserWidth, theUserHeight));
        myTetrisBoard.add(myTetrisBoardJPanel);
        myTetrisBoardJPanel.setVisible(true);
        myTetrisBoard.setVisible(true);
        myWidth = (int)myTetrisBoardJPanel.getCurrentSize().getWidth();
        myHeight = (int)myTetrisBoardJPanel.getCurrentSize().getHeight();
        myIsClearingBoard = 0;
    }

    /**
     * Returns the Tetris board.
     *
     * @return the Tetris board.
     */
    public JPanel getTetrisBoard() {
        return myTetrisBoard;
    }

    /**
     * ...
     *
     * @param theEvent  A PropertyChangeEvent object describing the event source
     *                  and the property that has changed.
     */
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (TETRIS_BOARD_PROPERTY.equals(theEvent.getPropertyName())) {
            myTetrisBoardJPanel.repaint();
        }
        if ("Piece Location".equals(theEvent.getPropertyName())) {
            myXOffset = (myWidth - (COLUMNS * (myWidth / COLUMNS))) / TWO;
            myYOffset = (myHeight - (ROWS * (myHeight / ROWS))) / TWO;
            myCellWidth = myWidth / COLUMNS;
            myCellHeight = myHeight / ROWS;
            myTetrisBoardJPanel.myMovingCells.clear();
            final Point[] p = (Point[]) theEvent.getNewValue();

            for (Point p2 : p) {
                final int x = myXOffset + (p2.x() * myCellWidth);
                final int y = myYOffset + (p2.y() * myCellHeight);
                final Rectangle r = new Rectangle(x, y);
                r.setLocation(x, -(y - (myHeight - myCellHeight)));
                r.setSize(myCellWidth, myCellHeight);
                myTetrisBoardJPanel.myMovingCells.add(r);
            }
            myWidth = (int) myTetrisBoardJPanel.getCurrentSize().getWidth();
            myHeight = (int) myTetrisBoardJPanel.getCurrentSize().getHeight();
            System.out.println("Width: " + myWidth + " Height: " + myHeight);

            /*ArrayList<Rectangle> rList = new ArrayList(COLUMNS * ROWS);
            for (Rectangle r : myTetrisBoardJPanel.myPlacedCells) {
                System.out.println("x " + (int)r.getLocation().getX());
                int x = ((int)r.getLocation().getX());
                int y = ((int)r.getLocation().getY());
                System.out.println("y " + y);
                r.setLocation(x, (y));
                r.setSize(myCellWidth, myCellHeight);
                rList.add(r);
                System.out.println(rList);
            }
            myTetrisBoardJPanel.myPlacedCells.clear();
            for (Rectangle r : rList) {
                myTetrisBoardJPanel.myPlacedCells.add(r);
            }

             */

            myTetrisBoardJPanel.repaint();
        }
        if ("PlacedBlock".equals(theEvent.getPropertyName())) {
            Point p[] = (Point[])theEvent.getNewValue();
            for (Point p2 : p) {
                int x = myXOffset + (p2.x() * myCellWidth);
                int y = myYOffset + (p2.y() * myCellHeight);
                Rectangle r = new Rectangle(x,y);
                boolean canPass = true;
                if (myIsClearingBoard == 0) {
                    r.setLocation(x, -(y - (myHeight - myCellHeight)));
                } else if (-(y - (myHeight - myCellHeight))
                        != myYOffset + (myCellHeight * (ROWS + 1)) &&
                        (-(y - (myHeight - myCellHeight)) + (myCellHeight * myBoardClearLevel))
                                != myYOffset + (myCellHeight * (ROWS - (myIsClearingBoard - 1)))) {
                    r.setLocation(x, (-(y - (myHeight - myCellHeight)) + (myCellHeight)));
                    System.out.println(r);
                    System.out.println(-(y - (myHeight - myCellHeight)) + (myCellHeight * myBoardClearLevel));
                    System.out.println(myYOffset + (myCellHeight * (ROWS - (myIsClearingBoard - 1))));
                } else {
                    canPass = false;
                }
                r.setSize(myCellWidth, myCellHeight);
                if (!(r.getLocation().getY() > myHeight - myYOffset) && canPass) {
                    myTetrisBoardJPanel.myPlacedCells.add(r);
                }
            }
            myIsClearingBoard = 0;
            myTetrisBoardJPanel.repaint();


        }
        if ("Row Location".equals(theEvent.getPropertyName())) {
            ArrayList<Rectangle> rTemp = new ArrayList(COLUMNS * ROWS);
            myIsClearingBoard++;
            final int rowY = myHeight - myYOffset - (((int)theEvent.getNewValue() + 1) * (myCellHeight));
            System.out.println(rowY);
            System.out.println("b4 " + myTetrisBoardJPanel.myPlacedCells);
            for (Rectangle r : myTetrisBoardJPanel.myPlacedCells) {
                int tempY = (int)r.getY();
                if ((int)r.getY() < rowY) {
                    r.setLocation((int) r.getX(), ((int) r.getY()) + myCellHeight);
                }
                if (tempY != rowY) {
                    System.out.println(rowY + " " + (int)r.getY());
                    r.setLocation((int)r.getX(), (int)r.getY());
                    rTemp.add(r);
                }
            }
            myTetrisBoardJPanel.myPlacedCells.clear();
            for (Rectangle r : rTemp) {
                myTetrisBoardJPanel.myPlacedCells.add(r);
            }
            System.out.println(myTetrisBoardJPanel.myPlacedCells);
            rTemp.clear();

            System.out.println("myclearingboard " + myIsClearingBoard);
            myBoardClearLevel = (int)theEvent.getNewValue() + 1;

            myTetrisBoardJPanel.repaint();
        }
    }

    /**
     * Returns the Cells in the Tetris board.
     *
     * @return the Cells in the Tetris board.
     */
    public ArrayList<Rectangle> getCells() {
        return myTetrisBoardJPanel.getMyCells();
    }

    /**
     * ...
     */
    private static class TetrisBoardJPanel extends JPanel {
        /**
         * The number of columns on the Tetris board.
         */
        private final int myColumnCount = 10;
        /**
         * The number of rows on the Tetris board.
         */
        private final int myRowCount = 20;
        /**
         * An ArrayList of Cells.
         */
        private final ArrayList<Rectangle> myCells;
        /**
         * An ArrayList of moving Cells.
         */
        private final ArrayList<Rectangle> myMovingCells;
        /**
         * An ArrayList of placed Cells.
         */
        private final ArrayList<Rectangle> myPlacedCells;
        /**
         * A constructor for inner class TetrisBoardJPanel.
         */
        TetrisBoardJPanel() {
            myCells = new ArrayList<>(myColumnCount * myRowCount);
            myMovingCells = new ArrayList<>(myColumnCount * myRowCount);
            myPlacedCells = new ArrayList<>(myColumnCount * myRowCount);
        }

        /**
         * Returns the Cells on the Tetris board.
         *
         * @return the Cells on the Tetris board.
         */
        public ArrayList<Rectangle> getMyCells() {
            return myCells;
        }

        /**
         * Returns the preferred size of the Tetris board.
         *
         * @return the preferred size of the Tetris board.
         */
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(this.getParent().getHeight() / 2,
                    this.getParent().getHeight() - FIFTY);
        }

        /**
         * Returns the current size of the Tetris board.
         *
         * @return the current size of the Tetris board.
         */
        public Dimension getCurrentSize() {
            return new Dimension(getWidth(), getHeight());
        }

        /**
         * ...
         */
        @Override
        public void invalidate() {
            myCells.clear();
            super.invalidate();
        }
        /**
         * Draws the Tetris pieces on the board.
         *
         * @param theG the Graphics2D object to protect
         */
        protected void paintComponent(final Graphics theG) {
            super.paintComponent(theG);
            final Graphics2D g2d = (Graphics2D) theG.create();

            final int width = getWidth();
            final int height = getHeight();

            final int cellWidth = width / myColumnCount;
            final int cellHeight = height / myRowCount;

            final int xOffset = (width - (myColumnCount * cellWidth)) / 2;
            final int yOffset = (height - (myRowCount * cellHeight)) / 2;

            for (int row = 0; row < myRowCount; row++) {
                for (int col = 0; col < myColumnCount; col++) {
                    final Rectangle cell = new Rectangle(xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight), cellWidth, cellHeight);
                    myCells.add(cell);
                }
            }

            g2d.setColor(Color.GRAY);
            for (Rectangle cell : myCells) {
                g2d.draw(cell);
            }

            g2d.setColor(Color.RED);
            for (Rectangle cell : myMovingCells) {
                g2d.fill(cell);
            }

            g2d.setColor(Color.GREEN);
            for (Rectangle cell : myPlacedCells) {
                g2d.fill(cell);
            }

            g2d.dispose();
        }
    }

    /**
     * Plays background music during the Tetris game.
     *
     * @param theFileName the name of the music file.
     */
    public void play(final String theFileName) {

        try {
            System.out.println(theFileName);
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(theFileName)));
            clip.start();
        } catch (final Exception e) {
            System.out.println("File not found");
        }
    }
}
