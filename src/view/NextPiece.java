package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

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


    public NextPiece(final int theUserWidth) {
        myNextPiece = new JPanel();
        this.myNextPiece.setBackground(Color.blue);
        this.myNextPiece.addPropertyChangeListener(this);
        this.myNextPiece.setPreferredSize(
                new Dimension(theUserWidth / FOUR, theUserWidth / FOUR));
    }

    public JPanel getNextPiece() {
        return myNextPiece;
    }

    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (NEW_PIECE_PROPERTY.equals(theEvent.getPropertyName())) {
            this.myNextPiece.setBackground(Color.yellow);
        }
    }
}
