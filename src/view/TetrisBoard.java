package view;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class TetrisBoard implements PropertyChangeListener {
    /**make tetris board.*/
    private JPanel myTetrisBoard;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    private static final String TETRIS_BOARD_PROPERTY = "TetrisBoardChange";

    public TetrisBoard(int theUserWidth, int theUserHeight) {
        myTetrisBoard = new JPanel();
        this.myTetrisBoard.setBackground(Color.red);
        this.myTetrisBoard.addPropertyChangeListener(this);
        myTetrisBoard.setPreferredSize(new Dimension(theUserWidth / FOUR, theUserHeight));
    }

    public JPanel getTetrisBoard() {
        return myTetrisBoard;
    }

    public void propertyChange(PropertyChangeEvent theEvent) {
        if (TETRIS_BOARD_PROPERTY.equals(theEvent.getPropertyName())) {
            this.myTetrisBoard.setBackground(Color.magenta);
        }
    }
}
