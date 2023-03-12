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
     * Property value for New Piece Create.
     */
    private static final String NEW_PIECE_PROPERTY = "NewPieceCreate";
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    /**Next piece panel.*/
    private final JPanel myNextPiece;

    /**
     * ...
     *
     * @param theUserWidth ...
     */
    public NextPiece(final int theUserWidth) {
        myNextPiece = new JPanel();
        this.myNextPiece.setBackground(Color.blue);
        this.myNextPiece.addPropertyChangeListener(this);
        this.myNextPiece.setPreferredSize(
                new Dimension(theUserWidth / FOUR, theUserWidth / FOUR));
    }

    /**
     * ...
     *
     * @return ...
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
            this.myNextPiece.setBackground(Color.yellow);
            this.myNextPiece.setToolTipText("Next Piece: " + theEvent.getNewValue());
        }
    }
}
