package view;

import model.Board;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class UserInfo implements PropertyChangeListener {
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    /**
     * Property value for New Piece Create.
     */
    private static final String NEW_PIECE_PROPERTY = "NewPieceCreate";
    /**
     * The amount of cleared lines needed to reach the next level.
     */
    private static final int LINES_TO_LEVELUP = 5;
    /**
     * Property value for Row Clear Change.
     */
    private static final String ROW_CLEAR = "Row Location";
    /**make user info.*/
    private final JPanel myUserInfo;
    /**
     * User Points Value.
     */
    private int myUserPoints;
    /**
     * User Lines Cleared.
     */
    private int myLinesCleared;
    private Board myBoard;
    /**
     * Timer delay instance variable.
     */
    private int myTimerTick;
    /**
     * Difficulty level instance variable.
     */
    private int myLevel;

    public UserInfo(final int theUserWidth, final int theUserHeight, final int theTimerTick) {
        myUserInfo = new JPanel();
        this.myUserInfo.setBackground(Color.green);
        this.myUserInfo.addPropertyChangeListener(this);
        myUserInfo.setPreferredSize(new Dimension(theUserWidth / FOUR, theUserHeight));
        myUserPoints = 0;
        myTimerTick = theTimerTick;
        myLevel = 1;
    }

    public JPanel getUserInfo() {
        return myUserInfo;
    }

    public int getTimerTick() {
        return myTimerTick;
    }

    public void propertyChange(final PropertyChangeEvent theEvent) {
        int tempLinesCleared = myLinesCleared;
        if (ROW_CLEAR.equals(theEvent.getPropertyName())) {
            myLinesCleared += 1;
            this.myUserInfo.setToolTipText("Points: " + myUserPoints
                    + " Level: " + myLevel + " Lines Cleared: " + myLinesCleared);
            if ((myLinesCleared % LINES_TO_LEVELUP) == 0 && myLinesCleared != 0) {
                myLevel++;
                myTimerTick = (int)((double)myTimerTick / 1.5);
            }
        }
        if (NEW_PIECE_PROPERTY.equals(theEvent.getPropertyName())) {
            myUserPoints += 4;
            this.myUserInfo.setToolTipText("Points: " + myUserPoints
                    + " Level: " + myLevel + " Lines Cleared: " + myLinesCleared);
        }
        if (myLinesCleared == tempLinesCleared + 1) {
            myUserPoints += 40 * myLevel;
        } else if (myLinesCleared == tempLinesCleared + 2) {
            myUserPoints += 100 * myLevel;
        } else if (myLinesCleared == tempLinesCleared + 3) {
            myUserPoints += 300 * myLevel;
        } else if (myLinesCleared == tempLinesCleared + 4) {
            myUserPoints += 1200 * myLevel;
        }
    }
}
