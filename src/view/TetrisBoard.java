package view;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class TetrisBoard implements PropertyChangeListener {
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    /**
     * Property value for Tetris Board Change.
     */
    private static final String TETRIS_BOARD_PROPERTY = "TetrisBoardChange";
    /**make tetris board.*/
    private final JPanel myTetrisBoard;

    public TetrisBoard(final int theUserWidth, final int theUserHeight) {
        myTetrisBoard = new JPanel();
        this.myTetrisBoard.setBackground(Color.black);
        this.myTetrisBoard.addPropertyChangeListener(this);
        myTetrisBoard.setPreferredSize(new Dimension(theUserWidth / FOUR, theUserHeight));
    }

    public JPanel getTetrisBoard() {
        return myTetrisBoard;
    }

    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (TETRIS_BOARD_PROPERTY.equals(theEvent.getPropertyName())) {

        }
    }
}
