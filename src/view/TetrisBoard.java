package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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
     * Avoid checkstyle 'magic error' number.
     */
    private static final int TWO = 2;
    /**
     * Avoid checkstyle 'magic error' number for amount of columns.
     */
    private static final int COLUMNS = 10;
    /**
     * Avoid checkstyle 'magic error' number for amount of rows.
     */
    private static final int ROWS = 20;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FORTY_EIGHT = 48;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FIFTY = 50;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FIVE_HUNDRED_NINE = 509;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int NINE_HUNDRED_SIXTY_EIGHT = 968;
    /**
     * Property value for Tetris Board Change.
     */
    private static final String TETRIS_BOARD_PROPERTY = "TetrisBoardChange";
    /**make tetris board.*/
    private final JPanel myTetrisBoard = new JPanel();
    /**panel width.*/
    private final int myWidth;
    /**panel height.*/
    private final int myHeight;

    /**
     * make board.
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
        myWidth = myTetrisBoardJPanel.getWidth();
        myHeight = myTetrisBoardJPanel.getHeight();
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
            final int xOffset = (509 - (COLUMNS * (509 / COLUMNS))) / TWO;
            final int yOffset = (968 - (ROWS * (968 / ROWS))) / TWO;
            myTetrisBoardJPanel.myMovingCells.clear();
            final Point[] p = (Point[]) theEvent.getNewValue();

            for (Point p2 : p) {
                final int x = xOffset + (p2.x() * (509 / COLUMNS));
                final int y = yOffset + (p2.y() * (968 / ROWS));
                final Rectangle r = new Rectangle(x, y);
                r.setLocation(x, -(y - (NINE_HUNDRED_SIXTY_EIGHT
                        - (NINE_HUNDRED_SIXTY_EIGHT / ROWS))));
                r.setSize(FIFTY, FORTY_EIGHT);
                myTetrisBoardJPanel.myMovingCells.add(r);
            }
            myTetrisBoardJPanel.repaint();
        }
        if ("PlacedBlock".equals(theEvent.getPropertyName())) {
            final int xOffset = (509 - (COLUMNS * (509 / COLUMNS))) / TWO;
            final int yOffset = (968 - (ROWS * (968 / ROWS))) / TWO;
            final Point[] p = (Point[]) theEvent.getNewValue();

            for (Point p2 : p) {
                final int x = xOffset + (p2.x() * (509 / COLUMNS));
                final int y = yOffset + (p2.y() * (968 / ROWS));
                final Rectangle r = new Rectangle(x, y);
                r.setLocation(x, -(y - (NINE_HUNDRED_SIXTY_EIGHT
                        - (NINE_HUNDRED_SIXTY_EIGHT / ROWS))));
                r.setSize(FIFTY, FORTY_EIGHT);
                myTetrisBoardJPanel.myPlacedCells.add(r);
            }
            myTetrisBoardJPanel.repaint();
        }
        if ("Row Location".equals(theEvent.getPropertyName())) {
            final ArrayList<Rectangle> rTemp = new ArrayList<>(COLUMNS * ROWS);
            final int rowY = 916 - ((int) theEvent.getNewValue() * 48);
            for (Rectangle r : myTetrisBoardJPanel.myPlacedCells) {
                if ((int) r.getY() != rowY) {
                    r.setLocation((int) r.getX(), (int) r.getY()
                            + (FIVE_HUNDRED_NINE / COLUMNS));
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
         *
         * @return ...
         */
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(this.getParent().getHeight() / 2,
                    this.getParent().getHeight() - FIFTY);
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
}
