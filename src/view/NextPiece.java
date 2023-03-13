package view;

import model.Board;
import model.TetrisPiece;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import static java.awt.Color.RED;

public class NextPiece implements PropertyChangeListener, Observer {
    /**
     * Property value for New Piece Create.
     */
    private static final String NEW_PIECE_PROPERTY = "NewPieceCreate";
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    private static final int TEN = 10;
    /**Next piece panel.*/
    private final JPanel myNextPiece;

    private static final int COLUMNS = 5;
    private static final int ROWS = 3;
    private static final int TWO = 2;

    private int myWidth;
    private int myHeight;
    private int myXOffset;
    private int myYOffset;
    private int myCellWidth;
    private int myCellHeight;


    private final NextPieceJPanel myNextPieceJPanel = new NextPieceJPanel();

    public NextPiece(final int theUserWidth, final int theUserHeight, final Board theBoard) {
        myNextPiece = new JPanel();
        this.myNextPiece.setBackground(Color.blue);
        this.myNextPiece.addPropertyChangeListener(this);
        this.myNextPiece.setPreferredSize(
                new Dimension(theUserWidth / FOUR, theUserWidth / FOUR));
        myWidth = (int) myNextPieceJPanel.getCurrentSize().getWidth();
        myHeight = (int) myNextPieceJPanel.getCurrentSize().getHeight();


    }

    public JPanel getNextPiece() {
        return myNextPiece;
    }



    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (NEW_PIECE_PROPERTY.equals(theEvent.getPropertyName())) {
            this.myNextPiece.setToolTipText("Next Piece: " + theEvent.getNewValue());

            if ("Next Piece".equals(theEvent.getPropertyName())) {
                myXOffset = (myWidth - (COLUMNS * (myWidth / COLUMNS))) / TWO;
                myYOffset = (myHeight - (ROWS * (myHeight / ROWS))) / TWO;
                myCellHeight = myHeight / ROWS;
                myCellWidth = myWidth / COLUMNS;
                myNextPieceJPanel.repaint();

                myWidth = (int) myNextPieceJPanel.getCurrentSize().getWidth();
                myHeight = (int) myNextPieceJPanel.getCurrentSize().getHeight();

                myNextPieceJPanel.repaint();
                System.out.println("fail");
            }

        }
    }

    public static class NextPieceJPanel extends JPanel {
        private final int myColumnCount = 6;
        private final int myRowCount = 3;
        private final ArrayList<Rectangle> myCells;

        NextPieceJPanel() {
            myCells = new ArrayList<>(myColumnCount * myRowCount);
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(this.getParent().getHeight() / 2, this.getParent().getHeight() - TEN);
        }
        public Dimension getCurrentSize() {
            return new Dimension(getWidth(), getHeight());
        }

        protected void paintComponent(final Graphics theGraphics) {
            super.paintComponent(theGraphics);
            final Graphics2D g2d = (Graphics2D) theGraphics.create();
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
                g2d.fill(cell);
            }
            g2d.setColor(Color.RED);
            g2d.dispose();
        }

    }
    @Override
    public void update(Observable o, Object arg) {
    }
}

