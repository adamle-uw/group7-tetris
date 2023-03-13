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
 * This program ...
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
    /**make tetris board.*/
    private final JPanel myTetrisBoard = new JPanel();
    /**The width of the board.*/
    private int myWidth;
    /**The height of the board.*/
    private int myHeight;
    /**X Offset of Cells in board.*/
    private int myXOffset;
    /**Y Offset of Cells in board.*/
    private int myYOffset;
    /**Cell width in board.*/
    private int myCellWidth;
    /**Cell height in board.*/
    private int myCellHeight;

    /**
     * The JPanel that displays the Tetris game board. //which does what?
     */
    private final TetrisBoardJPanel myTetrisBoardJPanel = new TetrisBoardJPanel();

    /**
     * ...
     *
     * @param theUserWidth ...
     * @param theUserHeight ...
     * @param theBoard ...
     */
    public TetrisBoard(final int theUserWidth, final int theUserHeight, final Board theBoard) {
        this.myTetrisBoard.setBackground(Color.black);
        this.myTetrisBoard.addPropertyChangeListener(this);
        myTetrisBoard.setPreferredSize(new Dimension(theUserWidth, theUserHeight));
        myTetrisBoard.add(myTetrisBoardJPanel);
        myTetrisBoardJPanel.setVisible(true);
        myTetrisBoard.setVisible(true);
        myWidth = (int) myTetrisBoardJPanel.getCurrentSize().getWidth();
        myHeight = (int) myTetrisBoardJPanel.getCurrentSize().getHeight();
    }

    /**
     * ...
     *
     * @return ...
     */
    public JPanel getTetrisBoard() {
        return myTetrisBoard;
    }

    /**
     * ...
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
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
            final Point[] p = (Point[]) theEvent.getNewValue();

            for (Point p2 : p) {
                final int x = myXOffset + (p2.x() * myCellWidth);
                final int y = myYOffset + (p2.y() * myCellHeight);
                final Rectangle r = new Rectangle(x, y);
                r.setLocation(x, -(y - (myHeight - myCellHeight)));
                r.setSize(myCellWidth, myCellHeight);
                myTetrisBoardJPanel.myPlacedCells.add(r);
            }
            myTetrisBoardJPanel.repaint();
        }
        if ("Row Location".equals(theEvent.getPropertyName())) {
            final ArrayList<Rectangle> rTemp = new ArrayList(COLUMNS * ROWS);
            final int rowY = myHeight - myYOffset
                    - ((int) theEvent.getNewValue() * (myHeight / ROWS));
            System.out.println(rowY);
            for (Rectangle r : myTetrisBoardJPanel.myPlacedCells) {
                if ((int) r.getY() != rowY) {
                    System.out.println(rowY + " " + (int) r.getY());
                    r.setLocation((int) r.getX(), (int) r.getY() + (myHeight / ROWS));
                    rTemp.add(r);
                }
            }
            myTetrisBoardJPanel.myPlacedCells.clear();
            for (Rectangle r : rTemp) {
                myTetrisBoardJPanel.myPlacedCells.add(r);
            }
            System.out.println(myTetrisBoardJPanel.myPlacedCells);
            rTemp.clear();
            myTetrisBoardJPanel.repaint();
        }
    }

    /**
     * ...
     *
     * @return ...
     */
    public ArrayList<Rectangle> getCells() {
        return myTetrisBoardJPanel.getMyCells();
    }

    /**
     * ...
     */
    private static class TetrisBoardJPanel extends JPanel {
        /**
         * ...
         */
        private final int myColumnCount = 10;
        /**
         * ...
         */
        private final int myRowCount = 20;
        /**
         * ...
         */
        private final ArrayList<Rectangle> myCells;
        /**
         * ...
         */
        private final ArrayList<Rectangle> myMovingCells;
        /**
         * ...
         */
        private final ArrayList<Rectangle> myPlacedCells;
        /**
         * ...
         */
        TetrisBoardJPanel() {
            myCells = new ArrayList<>(myColumnCount * myRowCount);
            myMovingCells = new ArrayList<>(myColumnCount * myRowCount);
            myPlacedCells = new ArrayList<>(myColumnCount * myRowCount);
        }

        /**
         * ...
         * @return ...
         */
        public ArrayList<Rectangle> getMyCells() {
            return myCells;
        }

        /**
         * ...
         * @return ...
         */
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(this.getParent().getHeight() / 2,
                    this.getParent().getHeight() - FIFTY);
        }

        /**
         * ...
         * * @return ...
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
         * ...
         *
         * @param theG the <code>Graphics</code> object to protect
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
