package model;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NextPiece implements PropertyChangeListener {
    /**Next piece panel.*/
    private JPanel myNextPiece;
    private static final String NEW_PIECE_PROPERTY = "NewPieceCreate";
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;


    public NextPiece(int theUserWidth) {
        myNextPiece = new JPanel();
        this.myNextPiece.setBackground(Color.blue);
        this.myNextPiece.addPropertyChangeListener(this);
        this.myNextPiece.setPreferredSize(new Dimension(theUserWidth / FOUR, theUserWidth / FOUR));
    }

    public JPanel getNextPiece() {
        return myNextPiece;
    }

    public void propertyChange(PropertyChangeEvent theEvent) {
        if (NEW_PIECE_PROPERTY.equals(theEvent.getPropertyName())) {
            this.myNextPiece.setBackground(Color.yellow);
        }
    }
}
