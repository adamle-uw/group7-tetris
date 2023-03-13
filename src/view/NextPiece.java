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
import model.Point;


public class NextPiece implements PropertyChangeListener {
    /**
     * Property value for New Piece Create.
     */
    private static final String NEW_PIECE_PROPERTY = "NewPieceCreate";
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int TEN = 10;
    /**
     * A constant for dividing board measurements in half.
     */
    private static final int TWO = 2;
    /**Next piece panel.*/
    private final JPanel myNextPiece;
    /**The Width of the NextPiece panel.*/
    private int myWidth;
    /**The Height of the NextPiece panel.*/
    private int myHeight;
    /**The x Offset of the NextPiece panel.*/


    private final NextPieceJPanel myNextPieceJPanel = new NextPieceJPanel();

    public NextPiece(final int theUserWidth) {
        myNextPiece = new JPanel();
        this.myNextPiece.setBackground(Color.blue);
        this.myNextPiece.addPropertyChangeListener(this);
        this.myNextPiece.setPreferredSize(
                new Dimension(theUserWidth / FOUR, theUserWidth / FOUR));
        myNextPiece.add(myNextPieceJPanel);
        myWidth = (int) myNextPieceJPanel.getCurrentSize().getWidth();
        myHeight = (int) myNextPieceJPanel.getCurrentSize().getHeight();


    }

    public JPanel getNextPiece() {
        return myNextPiece;
    }



    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (NEW_PIECE_PROPERTY.equals(theEvent.getPropertyName())) {
            final int cellWidth = myWidth / (TEN / TWO);
            final int cellHeight = myHeight / TEN;
            myNextPieceJPanel.myCells.clear();
            final model.Point[] p = (model.Point[]) theEvent.getNewValue();

            for (Point p2 : p) {
                final int xOffset = (myWidth - (TEN * (myWidth / TEN))) / TWO;
                final int yOffset = (myHeight - ((TEN * TWO) * (myHeight
                        / (TEN * TWO)))) / TWO;
                final Rectangle r = new Rectangle(p2.x(), p2.y());
                final int x = xOffset + (p2.x() * cellWidth);
                final int y = yOffset + (p2.y() * cellHeight);
                r.setLocation(x + (FOUR * TEN), -(y - (myHeight - cellHeight * FOUR)));
                r.setSize(cellWidth, cellHeight);
                myNextPieceJPanel.myCells.add(r);
            }
            myWidth = (int) myNextPieceJPanel.getCurrentSize().getWidth();
            myHeight = (int) myNextPieceJPanel.getCurrentSize().getHeight();

            myNextPieceJPanel.repaint();

        }
    }

    public static class NextPieceJPanel extends JPanel {
        /**
         * Avoid checkstyle 'magic error' number.
         */
        private static final int THREE = 3;
        /**Container for all the cells.*/
        private final ArrayList<Rectangle> myCells;

        NextPieceJPanel() {
            myCells = new ArrayList<>((THREE * TWO) * THREE);
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(this.getParent().getHeight() / TWO,
                    this.getParent().getHeight() - TEN);
        }
        public Dimension getCurrentSize() {
            return new Dimension(getWidth(), getHeight());
        }

        protected void paintComponent(final Graphics theGraphics) {
            super.paintComponent(theGraphics);
            final Graphics2D g2d = (Graphics2D) theGraphics.create();

            g2d.setColor(Color.GRAY);
            for (Rectangle cell : myCells) {
                g2d.fill(cell);
            }
            g2d.setColor(Color.RED);
            g2d.dispose();
        }

    }
}

