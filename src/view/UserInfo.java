package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
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
public class UserInfo implements PropertyChangeListener {
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int THREE = 3;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FOUR = 4;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FIVE = 5;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int FORTY = 40;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int ONE_HUNDRED = 100;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int THREE_HUNDRED = 300;
    /**
     * Avoid checkstyle 'magic error' number.
     */
    private static final int TWELVE_HUNDRED = 1200;
    /**
     * Property value for New Piece Create.
     */
    private static final String NEW_PIECE_PROPERTY = "NewPieceCreate";
    /**
     * The amount of cleared lines needed to reach the next level.
     */
    private static final int LINES_TO_LEVEL_UP = 5;
    /**
     * The multiplier of how much each level speeds up the game.
     */
    private static final double LEVEL_SPEEDUP_RATE = 1.2;
    /**
     * Property value for Row Clear Change.
     */
    private static final String ROW_CLEAR = "Row Location";
    /**
     * ...
     */
    private static final String POINTS = "<html>Points: ";
    /**
     * ...
     */
    private static final String LEVEL = "<br/><br/>Level: ";
    /**
     * ...
     */
    private static final String LINES_CLEARED = "<br/><br/>Lines Cleared: ";
    /**
     * ...
     */
    private static final String LINES_UNTIL_NEXT_LEVEL = "<br/><br/>Lines until Next Level: ";
    /**
     * make user info.
     */
    private final JPanel myUserInfo;
    /**
     * User Points Value.
     */
    private int myUserPoints;
    /**
     * User Lines Cleared.
     */
    private int myLinesCleared;
    /**
     * Timer delay instance variable.
     */
    private int myTimerTick;
    /**
     * Difficulty level instance variable.
     */
    private int myLevel;
    /**
     * Label for the User Info text.
     */
    private final JLabel myLabel;
    /**
     * ...
     */
    private int myTempLinesCleared = myLinesCleared;
    private int myLastPoints = 0;



    /**
     * ...
     *
     * @param theUserWidth ...
     * @param theUserHeight ...
     * @param theTimerTick ...
     */
    public UserInfo(final int theUserWidth, final int theUserHeight, final int theTimerTick) {
        myUserInfo = new JPanel();
        this.myUserInfo.setBackground(Color.green);
        this.myUserInfo.addPropertyChangeListener(this);
        myUserInfo.setPreferredSize(new Dimension(theUserWidth / FOUR, theUserHeight));
        myUserPoints = 0;
        myTimerTick = theTimerTick;
        myLevel = 1;
        for(int i = 1; i < myLevel; i++) {
            myTimerTick = (int) ((double) myTimerTick / LEVEL_SPEEDUP_RATE);
            System.out.println(myTimerTick);
        }
        myUserInfo.setLayout(new BoxLayout(myUserInfo, BoxLayout.X_AXIS));
        myLabel = new JLabel(POINTS + myUserPoints
                + LEVEL + myLevel + LINES_CLEARED
                + myLinesCleared + LINES_UNTIL_NEXT_LEVEL
                + (FIVE - (myLinesCleared % FIVE)), JLabel.CENTER);
        myLabel.setFont(new Font("Arial", Font.PLAIN, FORTY));
        myUserInfo.add(myLabel);
    }

    /**
     * ...
     *
     * @return ...
     */
    public JPanel getUserInfo() {
        return myUserInfo;
    }

    /**
     * ...
     *
     * @return ...
     */
    public int getTimerTick() {
        return myTimerTick;
    }

    /**
     * ...
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (ROW_CLEAR.equals(theEvent.getPropertyName())) {
            myLinesCleared += 1;
            this.myLabel.setText(POINTS + myUserPoints
                    + LEVEL + myLevel + LINES_CLEARED
                    + myLinesCleared  + LINES_UNTIL_NEXT_LEVEL
                    + (LINES_TO_LEVEL_UP - (myLinesCleared % LINES_TO_LEVEL_UP)));
            if ((myLinesCleared % LINES_TO_LEVEL_UP) == 0 && myLinesCleared != 0) {
                myLevel++;
                myTimerTick = (int) ((double) myTimerTick / LEVEL_SPEEDUP_RATE);
            }
        }
        if (NEW_PIECE_PROPERTY.equals(theEvent.getPropertyName())) {
            myUserPoints += FOUR;
            myLastPoints = myUserPoints;
            this.myLabel.setText(POINTS + myUserPoints
                    + LEVEL + myLevel + LINES_CLEARED
                    + myLinesCleared + LINES_UNTIL_NEXT_LEVEL
                    + (LINES_TO_LEVEL_UP - (myLinesCleared % LINES_TO_LEVEL_UP)));
        }
        if (myLinesCleared == myTempLinesCleared + 1) {
            myUserPoints += FORTY * myLevel;
            myTempLinesCleared = myLinesCleared;
        }
        if (myLastPoints == (myUserPoints) - (160 * myLevel)) {
            myUserPoints -= (160 * myLevel);
            myUserPoints += (TWELVE_HUNDRED * myLevel);
            myLastPoints = myUserPoints;
        } else if (myLastPoints == (myUserPoints) - (120 * myLevel)) {
            myUserPoints -= (120 * myLevel);
            myUserPoints += (THREE_HUNDRED * myLevel);
            myLastPoints = myUserPoints;
        } else if (myLastPoints == (myUserPoints) - (80 * myLevel)) {
            myUserPoints -= (80 * myLevel);
            myUserPoints += (ONE_HUNDRED * myLevel);
            myLastPoints = myUserPoints;
        }

    }
}
