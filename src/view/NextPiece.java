package view;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

/**
 * This program ...
 *
 * @author Evan Abrahamson
 * @author Aryan Damle
 * @author Martha Emerson
 * @author Keegan Sanders
 * @version Winter 2023
 */
public class NextPiece implements PropertyChangeListener {
    /**
     * The property value for New Piece Create. //fix this description (unclear)
     */
    private static final String NEW_PIECE_PROPERTY = "NewPieceCreate";
    /**
     * A constant for dividing the total height or width of the Board.
     */
    private static final int FOUR = 4;
    /**
     * The JPanel that displays the next Tetris piece.
     */
    private final JPanel myNextPiece;

    /**
     * ...
     *
     * @param theUserWidth ...
     */
    public NextPiece(final int theUserWidth) {
        myNextPiece = new JPanel();
        this.myNextPiece.setBackground(Color.BLUE);
        this.myNextPiece.addPropertyChangeListener(this);
        this.myNextPiece.setPreferredSize(
                new Dimension(theUserWidth / FOUR, theUserWidth / FOUR));
    }

    /**
     * Returns the next Tetris piece.
     *
     * @return  the next Tetris piece.
     */
    public JPanel getNextPiece() {
        return myNextPiece;
    }

    /**
     * ...
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (NEW_PIECE_PROPERTY.equals(theEvent.getPropertyName())) {
            this.myNextPiece.setBackground(Color.YELLOW);
            this.myNextPiece.setToolTipText("Next Piece: " + theEvent.getNewValue());
        }
    }
}
